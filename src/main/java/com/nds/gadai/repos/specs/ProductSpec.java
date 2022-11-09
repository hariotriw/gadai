package com.nds.gadai.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.ProductModel;

public class ProductSpec implements Specification<ProductEntity>{
    private ProductModel productModel;

    public ProductSpec(ProductModel productModel) {
        super();
        this.productModel = productModel;
    }

    private String adminPaymentType(String s) {
        return GlobalConstant.ADMIN_PAYMENT_TYPE[Integer.valueOf(s)-1];
    }

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
        
        Predicate p = cb.and();

        // id criteria
        if (productModel.getId() != null) {
            p.getExpressions().add(cb.equal(root.get("id"), productModel.getId()));
        }

        // name criteria
        if (productModel.getName() != null && productModel.getName().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("name")),
            "%" + productModel.getName().toLowerCase() + "%"));
        }

        // type criteria
        if (productModel.getType() != null && productModel.getType().length() > 0) {
            String business = adminPaymentType(productModel.getType());
            p.getExpressions().add(cb.like(cb.lower(root.get("type")),
            "%" + business.toLowerCase() + "%"));
        }

        // ltv criteria
        if (productModel.getLtvLow() != null && productModel.getLtvHigh() != null) {
            p.getExpressions().add(cb.between(root.get("ltv"), productModel.getLtvLow(), productModel.getLtvHigh()));
        }

        // saving service criteria
        if (productModel.getSavingServicePercentLow() != null && productModel.getSavingServicePercentHigh() != null) {
            p.getExpressions().add(cb.between(root.get("ltv"), productModel.getLtvLow(), productModel.getLtvHigh()));
        }

        // rec_status criteria
        if (productModel.getRecStatus() != null &&
        (productModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
        || productModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))) {

            p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")), 
            productModel.getRecStatus().toUpperCase()));
        }

        cq.orderBy(cb.asc(root.get("id")));

        return p;
    }
}
