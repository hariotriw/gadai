package com.nds.gadai.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.FixedInstallmentEntity;
import com.nds.gadai.globals.GlobalConstant;

@Repository
public interface FixedInstallmentRepo extends JpaRepository<FixedInstallmentEntity, String>, JpaSpecificationExecutor<FixedInstallmentEntity>{
    @Query(value = "SELECT * FROM ms_product WHERE rec_status = '"
                    + GlobalConstant.REC_STATUS_ACTIVE
                    + "'AND LOWER(transaction_number) = LOWER(:id)",nativeQuery = true)
        Optional<FixedInstallmentEntity> findById(@Param("id") String id);  
}
