package com.nds.gadai.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nds.gadai.entities.PawnedGoodsEntity;

@Repository
public interface PawnedGoodsRepo extends JpaRepository<PawnedGoodsEntity, String>, JpaSpecificationExecutor<PawnedGoodsEntity>{
    
    @Query(value = "SELECT * FROM tx_pawned_goods where transaction_number = ?1"
    , nativeQuery = true)
    List<PawnedGoodsEntity> getPawnedGoodsByTransactionNumber(String transactionNumber);
}
