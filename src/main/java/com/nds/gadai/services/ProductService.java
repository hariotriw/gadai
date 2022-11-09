package com.nds.gadai.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.exceptions.ClientException;
import com.nds.gadai.exceptions.NotFoundException;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.ProductModel;
import com.nds.gadai.repos.ProductRepo;
import com.nds.gadai.repos.specs.ProductSpec;
import com.nds.gadai.validators.ProductValidator;

@Service
public class ProductService implements Serializable{
    @Autowired
    private ProductRepo productRepo;

    ProductValidator productValidator = new ProductValidator();

    public String adminPaymentType(String s) {
        return GlobalConstant.ADMIN_PAYMENT_TYPE[Integer.valueOf(s)-1];
    }

    public String productType(String s) {
        return GlobalConstant.PRODUCT_TYPE[Integer.valueOf(s)-1];
    }

    public ProductEntity doInsertProduk(ProductModel productModel) throws ClientException {
        productValidator.validateSavingServiceNumeric(productModel);
        productValidator.validateAdminOpeningCost(productModel);
        productValidator.validateAdminClosingCost(productModel);

        long count = productRepo.countById(productModel.getId());
        if (count > 0) {
            throw new ClientException("Product ID already existed");
        }

        // Process
        ProductEntity product = new ProductEntity();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setType(productType(productModel.getType()));
        product.setDescription(productModel.getDescription());
        product.setTenor(productModel.getTenor());
        product.setLtv(productModel.getLtv().doubleValue());
        product.setAdminOpeningType(adminPaymentType(productModel.getAdminOpeningType()));
        product.setAdminOpeningCost(productModel.getAdminOpeningCost().doubleValue());
        product.setAdminClosingType(adminPaymentType(productModel.getAdminClosingType()));
        product.setAdminClosingCost(productModel.getAdminClosingCost().doubleValue());
        product.setSavingServicePercent(productModel.getSavingServicePercent().doubleValue());
        product.setSavingServiceNumeric(productModel.getSavingServiceNumeric());
        product.setPenaltyBillPercent(productModel.getPenaltyBillPercent().doubleValue());
        product.setPenaltyBillNumeric(productModel.getPenaltyBillNumeric());
        product.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        product.setCreatorId(productModel.getActorId() == null ? "0" : productModel.getActorId());

        return productRepo.save(product);
    }

    public List<ProductEntity> doSearchProduk(ProductModel productModel) {
        List<ProductEntity> products = new ArrayList<>();
        ProductSpec specs = new ProductSpec(productModel);
        productRepo.findAll(specs).forEach(products::add);

        return products;
    }

    public ProductEntity doGetDetailProduk(ProductModel productModel) throws NotFoundException, ClientException {
        productValidator.nullCheckId(productModel.getId());

        ProductEntity product = productRepo.findById(productModel.getId()).orElse(null);
        productValidator.nullCheckObject(product);
        
        return product;
    }

    public ProductEntity doUpdateProduk(ProductModel productModel) throws ClientException, NotFoundException {
        productValidator.nullCheckId(productModel.getId());

        Long count = productRepo.countById(productModel.getId());
        if (count == 0) {
            throw new ClientException("Cannot find product with id: " + productModel.getId());
        }

        ProductEntity product = new ProductEntity();
        product = doGetDetailProduk(productModel);

        if (productModel.getName() != null) {
            product.setName(productModel.getName());
        }

        if (productModel.getDescription() != null) {
            product.setDescription(productModel.getDescription());
        }

        if (productModel.getLtv() != null) {
            product.setLtv(productModel.getLtv().doubleValue());
        }

        if (productModel.getTenor() != null) {
            if (productModel.getSavingServiceNumeric() == null) {
                productModel.setSavingServiceNumeric(product.getSavingServiceNumeric());
            }
            productValidator.validateSavingServiceNumeric(productModel);
            product.setTenor(productModel.getTenor());
        }

        if (productModel.getAdminOpeningType() != null) {
            if (productModel.getAdminOpeningCost() == null) {
                productModel.setAdminOpeningCost(BigDecimal.valueOf(product.getAdminOpeningCost().doubleValue()));
            }
            productValidator.validateAdminOpeningCost(productModel);
            product.setAdminOpeningType(adminPaymentType(productModel.getAdminOpeningType()));
        }

        if (productModel.getAdminOpeningCost() != null) {
            if (productModel.getAdminOpeningType() == null) {
                productModel.setAdminOpeningType(product.getAdminOpeningType());
            }
            productValidator.validateAdminOpeningCost(productModel);
            product.setAdminOpeningCost(productModel.getAdminOpeningCost().doubleValue());
        }

        if (productModel.getAdminClosingType() != null) {
            if (productModel.getAdminClosingCost() == null) {
                productModel.setAdminClosingCost(BigDecimal.valueOf(product.getAdminClosingCost()));
            }
            productValidator.validateAdminClosingCost(productModel);
            product.setAdminClosingType(adminPaymentType(productModel.getAdminClosingType()));
        }

        if (productModel.getAdminClosingCost() != null) {
            if (productModel.getAdminClosingType() == null) {
                productModel.setAdminClosingType(product.getAdminClosingType());
            }
            productValidator.validateAdminClosingCost(productModel);
            product.setAdminClosingCost(productModel.getAdminClosingCost().doubleValue());
        }

        if (productModel.getSavingServicePercent() != null) {
            product.setSavingServicePercent(productModel.getSavingServicePercent().doubleValue());
        }

        if (productModel.getSavingServiceNumeric() != null) {
            if (productModel.getTenor() == null) {
                productModel.setTenor(product.getTenor());
            }
            productValidator.validateSavingServiceNumeric(productModel);
            product.setSavingServiceNumeric(productModel.getSavingServiceNumeric());
        }

        if (productModel.getPenaltyBillPercent() != null) {
            product.setPenaltyBillPercent(productModel.getPenaltyBillPercent().doubleValue());
        }

        if (productModel.getPenaltyBillNumeric() != null) {
            product.setPenaltyBillNumeric(productModel.getPenaltyBillNumeric());
        }

        product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        product.setUpdaterId(productModel.getActorId() == null ? "0" : productModel.getActorId());
        
        return productRepo.save(product);
    }

    public ProductEntity doDeleteProduk(ProductModel productModel) throws ClientException, NotFoundException {
        productValidator.nullCheckId(productModel.getId());

        Long count = productRepo.countById(productModel.getId());
        if (count == 0) {
            throw new ClientException("Cannot find product with id: " + productModel.getId());
        }

        ProductEntity product = new ProductEntity();
        product = doGetDetailProduk(productModel);

        if (product.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Product id (" + productModel.getId() + ") is already deleted");
        }

        product.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        product.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        product.setDeleterId(productModel.getActorId() == null ? "0" : productModel.getActorId());

        return productRepo.save(product);
    }
}
