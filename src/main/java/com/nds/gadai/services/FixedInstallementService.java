package com.nds.gadai.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nds.gadai.entities.FixedInstallmentEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.models.CustomerModel;
import com.nds.gadai.models.FixedInstallmentModel;
import com.nds.gadai.models.PawnedGoods;
import com.nds.gadai.repos.CustomerRepo;
import com.nds.gadai.repos.FixedInstallmentRepo;
import com.nds.gadai.repos.InstallmentRepo;
import com.nds.gadai.repos.ProductRepo;
import com.nds.gadai.repos.interfaces.CustomerInfo;
import com.nds.gadai.repos.interfaces.ProductInfo;
import com.nds.gadai.repos.specs.CustomerSpec;
import com.nds.gadai.repos.specs.FixedInstallmentSpec;
import com.nds.gadai.validators.FixedInstallmentValidator;

@Service
public class FixedInstallementService {
    @Autowired
    private FixedInstallmentRepo fixedInstallmentRepo;

    @Autowired
    private PawnedGoods pawnedGoodsRepo;

    @Autowired
    private InstallmentRepo installmentRepo;

    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private ProductRepo productRepo;

    private FixedInstallmentValidator fixedInstallmentValidator;

    public List<ProductInfo> doGetListProduk() {
        List<ProductInfo> products = new ArrayList<>();
        productRepo.findAllProductInfo().forEach(products::add);
        return products;
    }

    public List<CustomerInfo> doSearchPelanggan(CustomerModel customerModel) {
        List<CustomerInfo> customers = new ArrayList<>();
        CustomerSpec specs = new CustomerSpec(customerModel);
        customerRepo.findAllCustomerInfo(specs).forEach(customers::add);
        return customers;
    }

    public List<FixedInstallmentEntity> doSearchTransCicTetap(FixedInstallmentModel fixedInstallmentModel) {
        List<FixedInstallmentEntity> fixedInstallments = new ArrayList<>();
        FixedInstallmentSpec specs = new FixedInstallmentSpec(fixedInstallmentModel);
        fixedInstallmentRepo.findAll(specs).forEach(fixedInstallments::add);
        return fixedInstallments;
    }

    public FixedInstallmentEntity doGetDetailCicTetap(String transactionNumber) throws NotFoundException, ClientException {
        fixedInstallmentValidator.nullCheckTransactionNumber(transactionNumber);
        fixedInstallmentValidator.validateTransactionNumber(transactionNumber);

        FixedInstallmentEntity fixedInstallment = fixedInstallmentRepo.findById(transactionNumber).orElse(null);
        fixedInstallmentValidator.nullCheckObject(fixedInstallment);
        return fixedInstallment;
    }

    
    public FixedInstallmentEntity doHitungTrxCicTetap(String productId, BigDecimal totalTaksiran, BigDecimal nilaiPencairanPelanggan, BigDecimal diskonAdmin) {
        FixedInstallmentEntity fixedInstallment = new FixedInstallmentEntity();
        return fixedInstallment;
    }
}
