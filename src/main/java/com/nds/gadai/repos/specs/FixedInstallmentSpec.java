package com.nds.gadai.repos.specs;

import java.sql.Timestamp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nds.gadai.entities.FixedInstallmentEntity;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.FixedInstallmentModel;

public class FixedInstallmentSpec implements Specification<FixedInstallmentEntity>{
    private FixedInstallmentModel fixedInstallmentModel;

    public FixedInstallmentSpec(FixedInstallmentModel fixedInstallmentModel) {
        super();
        this.fixedInstallmentModel = fixedInstallmentModel;
    }

    private Timestamp stringToDate(String s) {
        return Timestamp.valueOf(s + " 00:00:00");
    }

    @Override
    public Predicate toPredicate(Root<FixedInstallmentEntity> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
        
        Predicate p = cb.and();

        // transaction number criteria
        if (fixedInstallmentModel.getTransactionNumber() != null && fixedInstallmentModel.getTransactionNumber().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("id")), "%" + fixedInstallmentModel.getTransactionNumber().toLowerCase() + "%"));
        }

        // product number criteria
        if (fixedInstallmentModel.getProductId() != null && fixedInstallmentModel.getProductId().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("productId")),
            "%" + fixedInstallmentModel.getProductId().toLowerCase() + "%"));
        }

        // product name criteria
        if (fixedInstallmentModel.getProductName() != null && fixedInstallmentModel.getProductName().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("productName")),
            "%" + fixedInstallmentModel.getProductName().toLowerCase() + "%"));
        }

        // customer number criteria
        if (fixedInstallmentModel.getCustomerId() != null && fixedInstallmentModel.getCustomerId().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("customerId")),
            "%" + fixedInstallmentModel.getCustomerId().toLowerCase() + "%"));
        }

        // customer name criteria
        if (fixedInstallmentModel.getCustomerName() != null && fixedInstallmentModel.getCustomerName().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("customerName")),
            "%" + fixedInstallmentModel.getCustomerName().toLowerCase() + "%"));
        }

        // Customer id number criteria
        if (fixedInstallmentModel.getCustomerIdNumber() != null && fixedInstallmentModel.getCustomerIdNumber().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("idNumber")),
            "%" + fixedInstallmentModel.getCustomerIdNumber().toLowerCase() + "%"));
        }

        // transfer date criteria
        if (fixedInstallmentModel.getTrxDateBefore() != null && fixedInstallmentModel.getTrxDateAfter() != null) {
            p.getExpressions().add(cb.between(root.get("transferDate"),
            stringToDate(fixedInstallmentModel.getTrxDateBefore()), stringToDate(fixedInstallmentModel.getTrxDateAfter())));
        }

        // rec_status criteria
        if (fixedInstallmentModel.getRecStatus() != null &&
        (fixedInstallmentModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
        || fixedInstallmentModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))) {

            p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")), 
            fixedInstallmentModel.getRecStatus().toUpperCase()));
        }

        cq.orderBy(cb.asc(root.get("id")));

        return p;
    }
}
