package com.nds.gadai.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nds.gadai.entities.CustomerEntity;
import com.nds.gadai.entities.FixedInstallmentEntiy;
import com.nds.gadai.entities.InstallmentEntity;
import com.nds.gadai.entities.PawnedGoodsEntity;
import com.nds.gadai.entities.PaymentEntity;
import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.models.PawnedGoods;
import com.nds.gadai.repos.CustomerRepo;
import com.nds.gadai.repos.FixedInstallmentRepo;
import com.nds.gadai.repos.InstallmentRepo;
import com.nds.gadai.repos.PawnedGoodsRepo;
import com.nds.gadai.repos.PaymentRepo;
import com.nds.gadai.repos.ProductRepo;
import com.nds.gadai.repos.UserRepo;
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

    public FixedInstallmentEntiy doGetDetailTransaction(String transactionNumber, String actorId) throws NotFoundException, ClientException {
        // authentikasi actor id
        userValidator.nullCheckActorId(actorId);
        Integer authCount = userRepo.countById(actorId);
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
        Integer productId = fixedInstallmentData.getProductId();
        List<InstallmentEntity> installmentsData = installmentRepo.getInstallmentsByTransactionNumber(transactionNumber);
        List<PawnedGoodsEntity> pawnedGoodsData = pawnedGoodsrepo.getPawnedGoodsByTransactionNumber(transactionNumber);
        CustomerEntity customerData = customerRepo.findByUserId(userId);
        PaymentEntity paymentData = paymentRepo.findByTransactionNumber(transactionNumber); 
        ProductEntity productData = productRepo.findByProductId(productId);

        // return object
        return fixedInstallmentData;
    }

    public Object doSearchPayFixedInstallment(Map<String,String> requestParams) throws NotFoundException, ClientException {
        Object result = new Object();

        // authentikasi actor id
        String actorId = requestParams.get("actorId");
        userValidator.nullCheckActorId(actorId);
        Integer authCount = userRepo.countById(actorId);
        if(authCount < 1){
            throw new NotFoundException("Actor id is not found");
        }

        // validation


        // process


        return result;
    }
    
}
