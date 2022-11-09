package com.nds.gadai.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.ProductEntity;
import com.nds.gadai.globals.GlobalConstant;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {

    @Query(value = "SELECT * FROM ms_product WHERE rec_status = '"
                    + GlobalConstant.REC_STATUS_ACTIVE
                    + "'AND LOWER(id) = LOWER(:id)",nativeQuery = true)
        Optional<ProductEntity> findById(@Param("id") String id);  
    
    @Query(value = "SELECT COUNT(*) FROM ms_product WHERE rec_status = '"
                    + GlobalConstant.REC_STATUS_ACTIVE
                    + "'AND LOWER(id) = LOWER(:id)",nativeQuery = true)
        long countById(@Param("id") String id);
}
