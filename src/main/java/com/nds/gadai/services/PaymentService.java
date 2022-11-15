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
        // authentikasi actor id
        userValidator.nullCheckActorId(actorId);
        Integer authCount = userRepo.countById(actorId);
        System.out.println(authCount);
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }

        // validation

        // comment output
        // part 1 informasi transaksi   ==> pelanggan (id - nama) | tgl transaksi | no transaksi | produk transaksi (tipe produk) | nama produk | keterangan produk 
        // part 2 daftar barang gadai   ==> no | nama barang | kondisi | jumlah | harga per satuan | jumlah
        // part 3 data kontrak          ==> *ada banyak
        // part 4 tagihan dan pembayaran==> kolom cicilan


        // process
        FixedInstallmentEntiy fixedInstallmentData = fixedInstallmentRepo.getFixedInstallmentByTransactionNumber(transactionNumber);
        String userId = fixedInstallmentData.getCustomerId();
        System.out.println(userId);
        Integer productId = fixedInstallmentData.getProductId();
        List<InstallmentEntity> installmentsData = installmentRepo.getInstallmentsByTransactionNumber(transactionNumber);
        List<PawnedGoodsEntity> pawnedGoodsData = pawnedGoodsrepo.getPawnedGoodsByTransactionNumber(transactionNumber);
        System.out.println("test 1");
        System.out.println("test 2");
        PaymentEntity paymentData = paymentRepo.findByTransactionNumber(transactionNumber); 
        ProductEntity productData = productRepo.findByProductId(productId);

        // CustomerEntity customerData = customerRepo.findByUserId(userId.toString());
        
        // return object

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("installments", installmentsData);
        result.put("pawnedGoods", pawnedGoodsData);
        result.put("fixedInstallment", fixedInstallmentData);
        result.put("payment", paymentData);
        result.put("product", productData);
        // return fixedInstallmentData;
        return result;
    }

    public List<FixedInstallmentEntiy> doSearchPayFixedInstallment(
        String transactionNumber,
        String customerId, 
        String customerName,
        Timestamp installmentStartDate,
        Timestamp installmentEndDate,
        String actorId
    ) throws NotFoundException, ClientException {

        // Testing Sysout the data
        System.out.println(transactionNumber);
        System.out.println(customerId);
        System.out.println(customerName);
        System.out.println(installmentStartDate);
        System.out.println(installmentEndDate);
        System.out.println(actorId);
        
        // authentikasi actor id
        userValidator.nullCheckActorId(actorId);
        Integer authCount = userRepo.countById(actorId);
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }

        // validation


        // process repo
        // List<FixedInstallmentEntiy> results = new ArrayList<>(); 

        // pake specs
        // SearchTransactionSpec specs = new SearchTransactionSpec(transactionNumber, customerId, customerName, installmentStartDate, installmentEndDate);
        // fixedInstallmentRepo.findAll(specs).forEach(results::add);

        // pake fungsi repo
        List<FixedInstallmentEntiy> results = fixedInstallmentRepo.findAllSearchTransaction(transactionNumber, customerId, customerName, installmentStartDate, installmentEndDate);
        // List<FixedInstallmentEntiy> results = fixedInstallmentRepo.findAllSearchTransaction(transactionNumber);

        // --- Belum selesai ---
        return results;

        // process service
        // HashMap<String, Object> result = new HashMap<String, Object>();
        // result.put("transactionNumber", 1);


        // return result;
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
