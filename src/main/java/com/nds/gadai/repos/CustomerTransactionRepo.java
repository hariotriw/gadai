package com.nds.gadai.repos;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.CustomerTransactionInfoEntity;

@Repository
public interface CustomerTransactionRepo extends JpaRepository<CustomerTransactionInfoEntity, Integer>, JpaSpecificationExecutor<CustomerTransactionInfoEntity> {
    // @Query(value = "SELECT i.id, fi.transaction_number, fi.transfer_date, fi.customer_id, c.id_number, fi.customer_name, i.installment_to, i.base_installment, i.late_penalty, i.installment_status, i.active_installment_date, i.rec_status " +

    @Query(value = "SELECT i.*, fi.*, c.* " +
    "FROM tx_fixed_installment as fi " +
    "JOIN tx_installment as i ON fi.transaction_number = i.transaction_number " +
    "JOIN ms_customer as c ON fi.customer_id = c.id " +
    "WHERE fi.rec_status = 'A' and fi.transaction_number LIKE %:transactionNumber% " +
    "AND fi.customer_id LIKE %:customerId% AND fi.customer_name LIKE %:customerName% " + 
    "AND i.active_installment_date >= :installmentStartDate " + 
    "AND i.active_installment_date <= :installmentEndDate", nativeQuery = true)
    List<CustomerTransactionInfoEntity> findAllSearchTransaction(
        @Param("transactionNumber") String transactionNumber,
        @Param("customerId") String customerId,
        @Param("customerName") String customerName,
        @Param("installmentStartDate") Timestamp installmentStartDate,
        @Param("installmentEndDate") Timestamp installmentEndDate
    );
    
}
