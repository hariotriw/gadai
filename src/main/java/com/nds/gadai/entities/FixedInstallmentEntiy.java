package com.nds.gadai.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tx_fixed_installment")
public class FixedInstallmentEntiy {
    
    @Id
    @GenericGenerator(name = "transaction_number_seq", strategy = "com.nds.gadai.generators.TransactionNumberGenerator")
    @GeneratedValue(generator = "transaction_number_seq")
    @JoinColumn(name = "transaction_number", referencedColumnName = "transaction_number")
    @Column(name = "transaction_number")
    private String transactionNumber;

    @OneToMany(targetEntity = InstallmentEntity.class, mappedBy = "transactionNumber")
    private List<InstallmentEntity> installments = new ArrayList<>();;

    @OneToMany(targetEntity = PawnedGoodsEntity.class, mappedBy = "transactionNumber")
    private List<PawnedGoodsEntity> pawnedGoods = new ArrayList<>();

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "transfer_date")
    private Timestamp transferDate;

    // belum ada relasinya
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "updater_id")
    private String updaterId;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @Column(name = "deleter_id")
    private String deleterId;
    
    @Column(name = "rec_status")
    private String recStatus;

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Timestamp getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Timestamp transferDate) {
        this.transferDate = transferDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeleterId() {
        return deleterId;
    }

    public void setDeleterId(String deleterId) {
        this.deleterId = deleterId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }
}
