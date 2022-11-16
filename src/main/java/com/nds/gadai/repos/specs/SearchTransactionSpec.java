package com.nds.gadai.repos.specs;

import java.sql.Timestamp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nds.gadai.entities.FixedInstallmentEntiy;

public class SearchTransactionSpec implements Specification<FixedInstallmentEntiy>{

    // private FixedInstallmentModel fixedInstallmentModel;
    private String transactionNumber;
    private String customerId;
    private String customerName;
    private Timestamp installmentStartDate;
    private Timestamp installmentEndDate;

    public SearchTransactionSpec(
        String transactionNumber,
        String customerId,
        String customerName,
        Timestamp installmentStartDate,
        Timestamp installmentEndDate) {
            super();
            this.transactionNumber = transactionNumber;
            this.customerId = customerId;
            this.customerName = customerName;
            this.installmentStartDate = installmentStartDate;
            this.installmentEndDate = installmentEndDate;
    }

    @Override
    public Predicate toPredicate(Root<FixedInstallmentEntiy> root, CriteriaQuery<?> cq, CriteriaBuilder cb){

        Predicate p = cb.and();

        // transaction Number criteria
        if(transactionNumber != null && transactionNumber.length() > 0){
            p.getExpressions().add(cb.like(cb.lower(root.get("transactionNumber")),
                "%" + transactionNumber.toLowerCase() + "%"));
        }

        // customer id criteria
        if(customerId != null && customerId.length() > 0){
            p.getExpressions().add(cb.like(cb.lower(root.get("customerId")),
                "%" + customerId.toLowerCase() + "%"));
        }

        // customer name criteria
        if(customerName != null && customerName.length() > 0){
            p.getExpressions().add(cb.like(cb.lower(root.get("customerName")),
                "%" + customerName.toLowerCase() + "%"));
        }

        // active installment criteria
        // if(customerName != null && customerName.length() > 0){
        //     p.getExpressions().add(cb.like(cb.lower(root.get("customerName")),
        //         "%" + customerName.toLowerCase() + "%"));
        // }

        // cq.orderBy(cb.asc(root.get("transactionNumber")));

        return p;
    }
    
}
