package com.nds.gadai.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.FixedInstallmentEntity;

@Repository
public interface FixedInstallmentRepo extends JpaRepository<FixedInstallmentEntity, String>, JpaSpecificationExecutor<FixedInstallmentEntity>{
    
}
