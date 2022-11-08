package com.nds.gadai.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nds.gadai.entities.UserEntity;
import com.nds.gadai.globals.GlobalConstant;
import com.nds.gadai.models.UserModel;

public class UserSpec implements Specification<UserEntity> {
    private UserModel userModel;

    public UserSpec(UserModel userModel){
        super();
        this.userModel = userModel;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb){

        Predicate p = cb.and();

        // id criteria
        if(userModel.getId() != null && userModel.getId().length() > 0){
            // p.getExpressions().add(cb.equal(root.get("id"), userModel.getId()));
            p.getExpressions().add(cb.like(cb.lower(root.get("id")),
                "%" + userModel.getId().toLowerCase() + "%"));
        }

        // name criteria
        if(userModel.getName() != null && userModel.getName().length() > 0){
            p.getExpressions().add(cb.like(cb.lower(root.get("name")),
                "%" + userModel.getName().toLowerCase() + "%"));
        }

        // description criteria
        if(userModel.getDescription() != null && userModel.getDescription().length() > 0){
            p.getExpressions().add(cb.like(cb.lower(root.get("description")),
                "%" + userModel.getDescription().toLowerCase() + "%"));
        }

        // phone number criteria
        if(userModel.getPhoneNumber() != null && userModel.getPhoneNumber().length() > 0 && userModel.getPhoneNumber().length() < 13){
            p.getExpressions().add(cb.like(cb.lower(root.get("phoneNumber")),
                "%" + userModel.getPhoneNumber().toLowerCase() + "%"));
        }

        // phone number criteria
        if(userModel.getActorId() != null){
            p.getExpressions().add(cb.equal(root.get("actorId"), userModel.getActorId()));
        }

        // rec_status criteria
        if(userModel.getRecStatus() != null && (userModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
                || userModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))){
            p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")), userModel.getRecStatus().toUpperCase()));
        }

        cq.orderBy(cb.asc(root.get("id")));

        return p;
    }
}
