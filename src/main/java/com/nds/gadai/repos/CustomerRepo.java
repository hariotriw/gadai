package com.nds.gadai.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.CustomerEntity;
import com.nds.gadai.globals.GlobalConstant;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {

    @Query(value = "SELECT * FROM ms_customer WHERE rec_status = '"
                    + GlobalConstant.REC_STATUS_ACTIVE
                    + "'AND LOWER(id) = LOWER(:id)",nativeQuery = true)
        Optional<CustomerEntity> findById(@Param("id") String id);  
    
    @Query(value = "SELECT COUNT(*) FROM ms_customer WHERE rec_status = '"
                    + GlobalConstant.REC_STATUS_ACTIVE
                    + "'AND LOWER(id) = LOWER(:id)",nativeQuery = true)
        long countById(@Param("id") String id);

    @Query(value = "SELECT COUNT(*) FROM ms_customer WHERE rec_status = '"
                    + GlobalConstant.REC_STATUS_ACTIVE
                    + "'AND LOWER(phone_number) = LOWER(:phone_number)",nativeQuery = true)
        long countByPhoneNumber(@Param("phone_number") String phoneNumber);

    @Query(value = "SELECT COUNT(*) FROM ms_customer WHERE rec_status = '"
                    + GlobalConstant.REC_STATUS_ACTIVE
                    + "'AND LOWER(id_number) = LOWER(:id_number)",nativeQuery = true)
        long countByIdNumber(@Param("id_number") String id);
}
