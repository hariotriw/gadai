package com.nds.gadai.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nds.gadai.entities.CustomerEntity;
import com.nds.gadai.entities.CustomerTransactionInfoEntity;
import com.nds.gadai.entities.FixedInstallmentEntiy;
import com.nds.gadai.entities.InstallmentEntity;
import com.nds.gadai.entities.PawnedGoodsEntity;
import com.nds.gadai.entities.PaymentEntity;
import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.FixedInstallmentModel;
import com.nds.gadai.models.PawnedGoods;
import com.nds.gadai.repos.CustomerRepo;
import com.nds.gadai.repos.CustomerTransactionRepo;
import com.nds.gadai.repos.FixedInstallmentRepo;
import com.nds.gadai.repos.InstallmentRepo;
import com.nds.gadai.repos.PawnedGoodsRepo;
import com.nds.gadai.repos.PaymentRepo;
import com.nds.gadai.repos.ProductRepo;
import com.nds.gadai.repos.UserRepo;
import com.nds.gadai.repos.specs.SearchTransactionSpec;
import com.nds.gadai.validators.UserValidator;

@Service
public class PaymentService implements Serializable{
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private FixedInstallmentRepo fixedInstallmentRepo;

    @Autowired
    private InstallmentRepo installmentRepo;

    @Autowired
    private PawnedGoodsRepo pawnedGoodsrepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

    UserValidator userValidator = new UserValidator();

    // doSearchUser
    // doGetDetailUser
    // doInsert

    // --- Testing ---
    public List<FixedInstallmentEntiy> testFind() {
        List<FixedInstallmentEntiy> tests = new ArrayList<>(); 
        fixedInstallmentRepo.findAll().forEach(tests::add);

        return tests;
    }

    public HashMap<String, Object> doGetDetailTransaction(String transactionNumber, String actorId) throws NotFoundException, ClientException {
        // ========== AUTHENTIKASI actor id ==========
        userValidator.nullCheckActorId(actorId);
        Integer authCount = userRepo.countById(actorId);
        System.out.println(authCount);
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }

        // ========== VALIDATION ==========


        // ========== PROCESS ========== 
        // -----inisialisasi object
        HashMap<String, Object> obj1 = new HashMap<String, Object>();
        HashMap<String, Object> obj2 = new HashMap<String, Object>();
        HashMap<String, Object> obj3 = new HashMap<String, Object>();
        HashMap<String, Object> obj4 = new HashMap<String, Object>();
        
        // ----- services & repos -----
        FixedInstallmentEntiy fixedInstallmentData = fixedInstallmentRepo.getFixedInstallmentByTransactionNumber(transactionNumber);
        String userId = fixedInstallmentData.getCustomerId();
        System.out.println(userId);
        Integer productId = fixedInstallmentData.getProductId();
        List<InstallmentEntity> installmentsData = installmentRepo.getInstallmentsByTransactionNumber(transactionNumber);
        List<PawnedGoodsEntity> pawnedGoodsData = pawnedGoodsrepo.getPawnedGoodsByTransactionNumber(transactionNumber);
        ProductEntity productData = productRepo.findByProductId(productId);

        // ----- processing data -----
        BigDecimal nilaiTaksiran = new BigDecimal( 0);
        for (PawnedGoodsEntity goods : pawnedGoodsData) {
            BigDecimal temp = new BigDecimal( 0);
            temp = goods.getPricePerUnit().multiply(new BigDecimal(goods.getAmount()));
            nilaiTaksiran = nilaiTaksiran.add(temp);
        }
        BigDecimal biayaAdminBuka = new BigDecimal(0);
        if(productData.getAdminOpeningType().equalsIgnoreCase("PERCENT")) {
            biayaAdminBuka = fixedInstallmentData.getCustomerLoan().multiply(new BigDecimal(productData.getAdminOpeningCost())).divide(new BigDecimal(100));
        }
        if(productData.getAdminOpeningType().equalsIgnoreCase("NOMINAL")) {
            biayaAdminBuka = fixedInstallmentData.getCustomerLoan().subtract(new BigDecimal(productData.getAdminOpeningCost()));
        }
        BigDecimal biayaAdminTutup = new BigDecimal(0);
        if(productData.getAdminClosingType().equalsIgnoreCase("PERCENT")) {
            biayaAdminTutup = fixedInstallmentData.getCustomerLoan().multiply(new BigDecimal(productData.getAdminClosingCost())).divide(new BigDecimal(100));
        }
        if(productData.getAdminClosingType().equalsIgnoreCase("NOMINAL")) {
            biayaAdminTutup = fixedInstallmentData.getCustomerLoan().subtract(new BigDecimal(productData.getAdminClosingCost()));
        }
        BigDecimal totalNilaiPinjaman = fixedInstallmentData.getCustomerLoan().add(biayaAdminBuka);
        

        // --- insert into obj1 ---
        obj1.put("pelanggan", fixedInstallmentData.getCustomerId().concat("-").concat(fixedInstallmentData.getCustomerName()));
        obj1.put("tanggalTransaksi", fixedInstallmentData.getTransferDate());
        obj1.put("noTransaksi", fixedInstallmentData.getTransactionNumber());
        obj1.put("produkTransaksi", fixedInstallmentData.getProductId());
        obj1.put("namaProduk", fixedInstallmentData.getProductName());
        obj1.put("descProduk", fixedInstallmentData.getProductDesc());

        // --- insert into obj2 ---
        obj2.put("daftarBarangGadai", pawnedGoodsData);

        // --- insert into obj3 ---
        obj3.put("totalNilaiTaksiran", nilaiTaksiran);
        obj3.put("ltv", productData.getLtv());
        obj3.put("maksNilaiPeminjaman", nilaiTaksiran.multiply(new BigDecimal(productData.getLtv())).divide(new BigDecimal(100)));
        obj3.put("biayaAdminBuka", biayaAdminBuka);
        obj3.put("nilaiPinjamanPelanggan", fixedInstallmentData.getCustomerLoan());
        obj3.put("totalNilaiPinjaman", totalNilaiPinjaman);
        obj3.put("tanggalTransaksi", fixedInstallmentData.getTransferDate());
        obj3.put("biayaAdminTUtup", biayaAdminTutup);
        obj3.put("totalPengembalian", totalNilaiPinjaman.add(biayaAdminTutup));
        // obj3.put("totalPengembalian", totalNilaiPinjaman.add(biayaAdminTutup).add(totalBiayaPenyimpanan));

        // --- insert into obj4 ---
        obj4.put("dataTagihan", installmentsData);

        System.out.println("test 1");
        System.out.println("test 2");

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("informasiTransaksi", obj1);
        result.put("daftarBarangGadai", obj2);
        result.put("dataKontrak", obj3);
        result.put("dataTagihan", obj4);
        
        return result;
    }

    public HashMap<String, Object> doSearchPayFixedInstallment(
        String transactionNumber,
        String customerId, 
        String customerName,
        Timestamp installmentStartDate,
        Timestamp installmentEndDate,
        String actorId
    ) throws NotFoundException, ClientException {

        // Testing Sysout the data
        // System.out.println(transactionNumber);
        // System.out.println(customerId);
        // System.out.println(customerName);
        // System.out.println(installmentStartDate);
        // System.out.println(installmentEndDate);
        // System.out.println(actorId);
        
        // authentikasi actor id
        userValidator.nullCheckActorId(actorId);
        Integer authCount = userRepo.countById(actorId);
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }

        // validation


        // pake fungsi repo
        System.out.println("test 8");
        List<CustomerTransactionInfoEntity> users = customerTransactionRepo.findAllSearchTransaction(transactionNumber, customerId, customerName, installmentStartDate, installmentEndDate);
        System.out.println("test 9");
        HashMap<String, Object> results = new HashMap<>();
        results.put("users", users);

        // --- Belum selesai ---
        return results;

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class})
    public HashMap<String, Object> doUpdatePembayaran(String transactionNumber, String paymentMethod, BigDecimal discount, BigDecimal totalPayment, List<Integer> selectedInstallment, String actorId) throws NotFoundException, ClientException {
        // authentikasi actor id
        userValidator.nullCheckActorId(actorId);
        Integer authCount = userRepo.countById(actorId);
        // System.out.println(authCount);
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }
        
        // validation
        
        
        // process
        HashMap<String, Object> result = new HashMap<String, Object>();
        // InstallmentEntity installmentEntity = null;
        BigDecimal totalBaseInstallment = new BigDecimal(0);
        BigDecimal totalPenalty = new BigDecimal(0);
        BigDecimal totalSaving = new BigDecimal(0);
        BigDecimal totalBill = new BigDecimal(0);
        BigDecimal roundingUp = new BigDecimal(0);
        BigDecimal adminCost = new BigDecimal(0);
        BigDecimal paymentTotal = new BigDecimal(0);
        BigDecimal change = new BigDecimal(0);
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

             
        // pre-pembayaran
        List<InstallmentEntity> installmentsData = installmentRepo.getInstallmentsByTransactionNumber(transactionNumber); // data 1 - 10
        
        // di tabel installment: status, tgl pembayaran
        // update tabel installment
        for(int i = 0; i < installmentsData.size(); i++) {
            if(selectedInstallment.contains(installmentsData.get(i).getInstallmentTo()) ) {
                InstallmentEntity installment = new InstallmentEntity();
                System.out.println(installmentsData.get(i).getId());
                System.out.println("test 1");
                installment = installmentRepo.findByInstallmentId(installmentsData.get(i).getId());
                System.out.println("test 2");

                // result.put("installments-" + i, installmentsData.get(i));
                totalBaseInstallment = totalBaseInstallment.add(installmentsData.get(i).getBaseInstallment());
                totalPenalty = totalPenalty.add(installmentsData.get(i).getLatePenalty());
                totalSaving = totalSaving.add(installmentsData.get(i).getSavingServiceCost());

                // installment.setInstallmentTo(installmentsData.get(i).getInstallmentTo());
                // installment.setBaseInstallment(installmentsData.get(i).getBaseInstallment());
                // installment.setSavingServiceCost(installmentsData.get(i).getSavingServiceCost());
                // installment.setLatePenalty(installmentsData.get(i).getLatePenalty());
                // installment.setTotalInstallment(installmentsData.get(i).getTotalInstallment());
                // installment.setActiveInstallmentDate(installmentsData.get(i).getActiveInstallmentDate());
                // installment.setCreatedDate(installmentsData.get(i).getCreatedDate());
                // installment.setCreatorId(installmentsData.get(i).getCreatorId());
                
                installment.setTransactionNumber(transactionNumber);
                installment.setInstallmentStatus("LUNAS");
                installment.setPaidDate(currentDate);
                installment.setUpdatedDate(currentDate);
                installment.setUpdaterId(actorId);
                
                installmentRepo.save(installment);
            }
        }
        totalBill = totalBill.add(totalBaseInstallment).add(totalPenalty).add(totalSaving).subtract(discount);
        paymentTotal = totalBill.subtract(roundingUp);
        change = totalPayment.subtract(paymentTotal);
        
        // System.out.println(installmentsData);
        
        // di tabel payment: no transaksi, total_installment_cost, total_installment_penalty, admin_cllosing_cost, total_bill, rounding_up, total_payment, payment_method
        // create pembayaran
        PaymentEntity payment = new PaymentEntity();
        payment.setTransactionNumber(transactionNumber);
        payment.setTotalInstallmentCost(totalBaseInstallment);
        payment.setTotalInstallmentPenalty(totalPenalty);
        payment.setAdminClosingCost(adminCost);
        payment.setTotalBill(totalBill);
        payment.setRoundingUp(roundingUp);
        payment.setTotalPayment(paymentTotal);
        payment.setPaymentMethod(paymentMethod);
        payment.setCreatedDate(currentDate);
        payment.setCreatorId(actorId);
        payment.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);

        paymentRepo.save(payment);

        result.put("totalBiayaPokok", totalBaseInstallment);
        result.put("totalDenda", totalPenalty);
        // result.put("totalBiayaPenyimpanan", totalSaving);
        result.put("biayaAdmin", adminCost);
        result.put("diskon", discount);
        result.put("totalPembayaran", totalBill);
        result.put("pembulatan", roundingUp);
        result.put("totalSetelahPembulatan", paymentTotal);
        result.put("jumlahDibayarPelanggan", totalPayment);
        result.put("kembalian", change);
        result.put("metodePembayaran", paymentMethod);
            
        return result;
    }
    
}
