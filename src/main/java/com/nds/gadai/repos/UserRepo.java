package com.nds.gadai.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.UserEntity;
import com.nds.gadai.globals.GlobalConstant;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

    // Query for find user detail by using user id
    @Query(value = "SELECT * FROM ms_user  WHERE rec_status = '"
        + GlobalConstant.REC_STATUS_ACTIVE
        + "' and id = LOWER(:id)", nativeQuery = true)
    UserEntity findByUserId(
        @Param("id") String id
    );

    // Query for count how many User Id on database
    @Query(value = "SELECT COUNT(*) FROM ms_user WHERE rec_status = '"
        + GlobalConstant.REC_STATUS_ACTIVE
        + "' AND LOWER(id) = LOWER(:id)", nativeQuery = true)
    Integer countById(@Param("id") String id);
    
}
