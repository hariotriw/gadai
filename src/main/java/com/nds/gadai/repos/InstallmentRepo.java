package com.nds.gadai.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.InstallmentEntity;

@Repository
public interface InstallmentRepo extends JpaRepository<InstallmentEntity, String>, JpaSpecificationExecutor<InstallmentEntity>{
    
    @Query(value = "SELECT * FROM tx_installment where transaction_number = ?1"
    , nativeQuery = true)
    List<InstallmentEntity> getInstallmentsByTransactionNumber(String transactionNumber);
}
