package com.nds.gadai.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nds.gadai.entities.CustomerEntity;
import com.nds.gadai.entities.FixedInstallmentEntity;
import com.nds.gadai.entities.PawnedGoodsEntity;
import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.ContractModel;
import com.nds.gadai.models.CustomerModel;
import com.nds.gadai.models.FixedInstallmentModel;
import com.nds.gadai.models.PawnedGoods;
import com.nds.gadai.repos.CustomerRepo;
import com.nds.gadai.repos.FixedInstallmentRepo;
import com.nds.gadai.repos.PawnedGoodsRepo;
import com.nds.gadai.repos.ProductRepo;
import com.nds.gadai.repos.interfaces.CustomerInfo;
import com.nds.gadai.repos.interfaces.ProductInfo;
import com.nds.gadai.repos.specs.CustomerSpec;
import com.nds.gadai.repos.specs.FixedInstallmentSpec;
import com.nds.gadai.validators.CustomerValidator;
import com.nds.gadai.validators.FixedInstallmentValidator;
import com.nds.gadai.validators.ProductValidator;

@Service
public class FixedInstallmentService {
    @Autowired
    private FixedInstallmentRepo fixedInstallmentRepo;

    FixedInstallmentValidator fixedInstallmentValidator = new FixedInstallmentValidator();
    ProductValidator productValidator = new ProductValidator();
    CustomerValidator customerValidator = new CustomerValidator();

    @Autowired
    private PawnedGoodsRepo pawnedGoodsRepo;

    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private ProductRepo productRepo;

    public List<ProductInfo> doGetListProduk() throws ClientException {
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

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    private PawnedGoodsEntity doSavePawnedGood(PawnedGoods pawnedGood) {
        PawnedGoodsEntity goods = new PawnedGoodsEntity();
        goods.setGoodsName(pawnedGood.getGoodsName());
        goods.setTransactionNumber(pawnedGood.getTransactionNumber());
        goods.setDescription(pawnedGood.getDescription());
        goods.setAmount(pawnedGood.getAmount());
        goods.setPricePerUnit(pawnedGood.getPricePerUnit());
        goods.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        goods.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        goods.setCreatorId(pawnedGood.getActorId() == null ? "0" : pawnedGood.getActorId());

        return pawnedGoodsRepo.save(goods);
    }

    private ProductEntity doGetDetailProduk(String id) throws NotFoundException, ClientException {
        productValidator.nullCheckId(id);
        productValidator.validateId(id);

        ProductEntity product = productRepo.findById(id).orElse(null);
        productValidator.nullCheckObject(product);
        
        return product;
    }

    private CustomerEntity doGetDetailPelanggan(String id) throws NotFoundException, ClientException {
        customerValidator.nullCheckId(id);
        customerValidator.validateId(id);

        CustomerEntity customer = customerRepo.findById(id).orElse(null);
        customerValidator.nullCheckObject(customer);
        
        return customer;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public ContractModel doHitungTrxCicTetap(ContractModel contract) throws ClientException, NotFoundException {
        // Look for product
        ProductEntity product = doGetDetailProduk(contract.getProductId());
        contract.setLtv(BigDecimal.valueOf(product.getLtv()));
        contract.setMaxLoan(contract.getLtv().multiply(contract.getTotalEstimatedValue()).divide(BigDecimal.valueOf(100)));

        if (product.getAdminOpeningType() == GlobalConstant.ADMIN_PAYMENT_TYPE[0]) {
            contract.setAdminOpeningCost(contract.getCustLiquidationValue().multiply(BigDecimal.valueOf(product.getAdminOpeningCost()).divide(BigDecimal.valueOf(100))));
        }
        else {
            contract.setAdminOpeningCost(BigDecimal.valueOf(product.getAdminOpeningCost()));
        }

        contract.setAdminOpeningCostFinal(contract.getAdminOpeningCost().multiply(BigDecimal.valueOf(1).subtract(contract.getAdminDiscount().multiply(BigDecimal.valueOf(0.01)))));
        contract.setTotalLoan(contract.getMaxLoan().add(contract.getAdminOpeningCost()));
        contract.setTransactionDate(new Timestamp(System.currentTimeMillis()));

        Calendar cal = Calendar.getInstance();
        cal.setTime(contract.getTransactionDate());
        cal.add(Calendar.MONTH, product.getTenor());
        contract.setDeadlineDate(new Timestamp(cal.getTime().getTime()));

        contract.setSavingServicePercent(BigDecimal.valueOf(product.getSavingServicePercent()));
        contract.setSavingServiceCost(((contract.getSavingServicePercent().divide(BigDecimal.valueOf(100))).multiply(contract.getTotalLoan())).divide(BigDecimal.valueOf(product.getTenor()).divide(BigDecimal.valueOf(product.getSavingServiceNumeric()))));
        contract.setSavingServiceTotal(BigDecimal.valueOf(product.getTenor()).divide(BigDecimal.valueOf(product.getSavingServiceNumeric())).multiply(contract.getSavingServiceCost()));
        
        if (product.getAdminClosingType() == GlobalConstant.ADMIN_PAYMENT_TYPE[0]) {
            contract.setAdminClosingCost(contract.getCustLiquidationValue().multiply(BigDecimal.valueOf(product.getAdminClosingCost()).divide(BigDecimal.valueOf(100))));
        }
        else {
            contract.setAdminClosingCost(BigDecimal.valueOf(product.getAdminClosingCost()));
        }

        contract.setTotalRepayment(contract.getTotalLoan().add(contract.getSavingServiceTotal().add(contract.getAdminClosingCost())));
        return contract;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public FixedInstallmentEntity doSaveTrxCicTetap(ContractModel contract, List<PawnedGoods> pawnedGoods) throws ClientException, NotFoundException {
        FixedInstallmentEntity fixedInstallment = new FixedInstallmentEntity();

        ProductEntity product = doGetDetailProduk(contract.getProductId());
        fixedInstallment.setProduct(product);

        CustomerEntity customer = doGetDetailPelanggan(contract.getCustomerId());
        fixedInstallment.setCustomer(customer);

        contract = doHitungTrxCicTetap(contract);
        fixedInstallment.setTotalEstimatedValue(contract.getTotalEstimatedValue().doubleValue());
        fixedInstallment.setCustLiquidationValue(contract.getCustLiquidationValue().doubleValue());
        fixedInstallment.setAdminDiscount(contract.getAdminDiscount().doubleValue());

        fixedInstallment.setTransferDate(new Timestamp(System.currentTimeMillis()));
        fixedInstallment.setCreatorId(contract.getActorId() == null ? "0" : contract.getActorId());

        fixedInstallment = fixedInstallmentRepo.save(fixedInstallment);
        
        List<PawnedGoodsEntity> goodsEntity = new ArrayList<>();
        for (PawnedGoods pawnedGood : pawnedGoods) {
            pawnedGood.setTransactionNumber(fixedInstallment.getTransactionNumber());
            goodsEntity.add(doSavePawnedGood(pawnedGood));
        }
        return fixedInstallment;
    }
}
