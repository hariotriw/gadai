package com.nds.gadai.repos;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.FixedInstallmentEntiy;
import com.nds.gadai.globals.GlobalConstant;

@Repository
public interface FixedInstallmentRepo extends JpaRepository<FixedInstallmentEntiy, String>, JpaSpecificationExecutor<FixedInstallmentEntiy>{
    
    // @Query(value = "SELECT fi.* FROM tx_fixed_installment as fi " +
    // "JOIN ms_customer as c ON fi.customer_id = c.id " +
    // "JOIN ms_product as p ON fi.product_id = p.id " +
    // "JOIN tx_installment as i ON fi.transaction_number = 'i.transaction_number'" +
    // "JOIN tx_pawned_goods as pg ON fi.transaction_number = pg.transaction_number " +
    // "WHERE fi.rec_status = '" + GlobalConstant.REC_STATUS_ACTIVE + "' and fi.transaction_number = ?1", nativeQuery = true)
    // List<FixedInstallmentEntiy> findDetailTransaction(String transactionNumber);

    @Query(value = "SELECT fi.*, i.installment_to as cicilan_ke, pg.goods_name FROM tx_fixed_installment as fi " +
    "JOIN ms_product as p ON fi.product_id = p.id " +
    "JOIN tx_installment as i ON fi.transaction_number = i.transaction_number " +
    "JOIN tx_pawned_goods as pg ON fi.transaction_number = pg.transaction_number " +
    "WHERE fi.rec_status = 'A' and fi.transaction_number = ?1", nativeQuery = true)
    List<FixedInstallmentEntiy> findDetailTransaction(String transactionNumber);

    @Query(value = "SELECT * FROM tx_fixed_installment where transaction_number = ?1"
    , nativeQuery = true)
    FixedInstallmentEntiy getFixedInstallmentByTransactionNumber(String transactionNumber);

    // @Query(value = "SELECT fi.transaction_number, fi.transfer_date, fi.customer_id, fi.customer_name, " +
    // "i.installment_to, i.total_installment, i.installment_status, i.active_installment_date, " +
    // "fi.created_date, fi.creator_id, fi.updated_date, fi.updater_id, fi.deleted_date, fi.deleter_id " +
    // "fi.product_id, fi.product_name, fi.product_desc " +
    // "FROM tx_fixed_installment as fi " +
    // "JOIN tx_installment as i ON fi.transaction_number = i.transaction_number " +
    // "WHERE fi.rec_status = 'A' and fi.transaction_number LIKE %:transactionNumber% " +
    // "AND fi.customer_id LIKE %:customerId% AND fi.customer_name LIKE %:customerName% " + 
    // "AND i.active_installment_date >= :installmentStartDate " + 
    // "AND i.active_installment_date <= :installmentEndDate", nativeQuery = true)
    // List<FixedInstallmentEntiy> findAllSearchTransaction(
    //     @Param("transactionNumber") String transactionNumber,
    //     @Param("customerId") String customerId,
    //     @Param("customerName") String customerName,
    //     @Param("installmentStartDate") Timestamp installmentStartDate,
    //     @Param("installmentEndDate") Timestamp installmentEndDate
    // );

    @Query(value = "SELECT fi.*, " +
    "i.installment_to, i.total_installment, i.installment_status, i.active_installment_date " +
    "FROM tx_fixed_installment as fi " +
    "JOIN tx_installment as i ON fi.transaction_number = i.transaction_number " +
    "WHERE fi.rec_status = 'A' and fi.transaction_number LIKE %:transactionNumber% " +
    "AND fi.customer_id LIKE %:customerId% AND fi.customer_name LIKE %:customerName% " + 
    "AND i.active_installment_date >= :installmentStartDate " + 
    "AND i.active_installment_date <= :installmentEndDate", nativeQuery = true)
    List<FixedInstallmentEntiy> findAllSearchTransaction(
        @Param("transactionNumber") String transactionNumber,
        @Param("customerId") String customerId,
        @Param("customerName") String customerName,
        @Param("installmentStartDate") Timestamp installmentStartDate,
        @Param("installmentEndDate") Timestamp installmentEndDate
    );

}
