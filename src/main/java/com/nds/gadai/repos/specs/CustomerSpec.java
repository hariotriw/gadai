package com.nds.gadai.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nds.gadai.entities.CustomerEntity;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.CustomerModel;

public class CustomerSpec implements Specification<CustomerEntity>{
    private CustomerModel customerModel;

    public CustomerSpec(CustomerModel customerModel) {
        super();
        this.customerModel = customerModel;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {
        
        Predicate p = cb.and();

        // id criteria
        if (customerModel.getId() != null) {
            p.getExpressions().add(cb.equal(root.get("id"), customerModel.getId()));
        }

        // name criteria
        if (customerModel.getName() != null && customerModel.getName().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("name")),
            "%" + customerModel.getName().toLowerCase() + "%"));
        }

        // id number criteria
        if (customerModel.getIdNumber() != null && customerModel.getIdNumber().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("idNumber")),
            "%" + customerModel.getIdNumber().toLowerCase() + "%"));
        }

        // phone number criteria
        if (customerModel.getPhoneNumber() != null && customerModel.getPhoneNumber().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("phoneNumber")),
            "%" + customerModel.getPhoneNumber().toLowerCase() + "%"));
        }

        // business type criteria
        if (customerModel.getBusinessType() != null && customerModel.getBusinessType().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("businessType")),
            "%" + customerModel.getBusinessType().toLowerCase() + "%"));
        }

        // rec_status criteria
        if (customerModel.getRecStatus() != null &&
        (customerModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
        || customerModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))) {

            p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")), 
            customerModel.getRecStatus().toUpperCase()));
        }

        cq.orderBy(cb.asc(root.get("id")));

        return p;
    }
}
