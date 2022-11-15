package com.nds.gadai.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.InstallmentEntity;
import com.nds.gadai.globals.GlobalConstant;

@Repository
public interface InstallmentRepo extends JpaRepository<InstallmentEntity, Integer>, JpaSpecificationExecutor<InstallmentEntity>{
    
    @Query(value = "SELECT * FROM tx_installment where transaction_number = ?1 ORDER BY id"
    , nativeQuery = true)
    List<InstallmentEntity> getInstallmentsByTransactionNumber(String transactionNumber);

    @Query(value = "SELECT * FROM tx_installment  WHERE rec_status = '"
        + GlobalConstant.REC_STATUS_ACTIVE
        + "' and id = ?1", nativeQuery = true)
    InstallmentEntity findByInstallmentId(
        Integer id
    );
}
