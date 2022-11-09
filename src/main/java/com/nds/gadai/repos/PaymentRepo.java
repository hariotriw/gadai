package com.nds.gadai.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.PaymentEntity;
import com.nds.gadai.globals.GlobalConstant;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentEntity, String>, JpaSpecificationExecutor<PaymentEntity>{
    
    @Query(value = "SELECT * FROM tx_payment WHERE rec_status = '"
                + GlobalConstant.REC_STATUS_ACTIVE
                + "'AND LOWER(transaction_number) = LOWER(:transactionNumber)",nativeQuery = true)
    PaymentEntity findByTransactionNumber(@Param("transactionNumber") String transactionNumber); 
}
