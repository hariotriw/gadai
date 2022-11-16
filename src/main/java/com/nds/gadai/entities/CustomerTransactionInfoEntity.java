package com.nds.gadai.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tx_fixed_installment")
public class CustomerTransactionInfoEntity {
    

    // ----- Fixed Installment -----
    @GenericGenerator(name = "transaction_number_seq", strategy = "com.nds.gadai.generators.TransactionNumberGenerator")
    @GeneratedValue(generator = "transaction_number_seq")
    @Column(name = "transaction_number")
    private String transactionNumber;

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

    // ----- Installment -----
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "installment_to")
    private Integer installmentTo;

    @Column(name = "base_installment")
    private BigDecimal baseInstallment;

    @Column(name = "saving_service_cost")
    private BigDecimal savingServiceCost;

    @Column(name = "late_penalty")
    private BigDecimal latePenalty;

    @Column(name = "total_installment")
    private BigDecimal totalInstallment;

    @Column(name = "installment_status")
    private String installmentStatus;

    @Column(name = "active_installment_date")
    private Timestamp activeInstallmentDate;

    @Column(name = "paid_date")
    private Timestamp paidDate;

    // ----- Customer -----
    @Column(name = "name")
    private String name;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "max_limit")
    private Double maxLimit;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstallmentTo() {
        return installmentTo;
    }

    public void setInstallmentTo(Integer installmentTo) {
        this.installmentTo = installmentTo;
    }

    public BigDecimal getBaseInstallment() {
        return baseInstallment;
    }

    public void setBaseInstallment(BigDecimal baseInstallment) {
        this.baseInstallment = baseInstallment;
    }

    public BigDecimal getSavingServiceCost() {
        return savingServiceCost;
    }

    public void setSavingServiceCost(BigDecimal savingServiceCost) {
        this.savingServiceCost = savingServiceCost;
    }

    public BigDecimal getLatePenalty() {
        return latePenalty;
    }

    public void setLatePenalty(BigDecimal latePenalty) {
        this.latePenalty = latePenalty;
    }

    public BigDecimal getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(BigDecimal totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public String getInstallmentStatus() {
        return installmentStatus;
    }

    public void setInstallmentStatus(String installmentStatus) {
        this.installmentStatus = installmentStatus;
    }

    public Timestamp getActiveInstallmentDate() {
        return activeInstallmentDate;
    }

    public void setActiveInstallmentDate(Timestamp activeInstallmentDate) {
        this.activeInstallmentDate = activeInstallmentDate;
    }

    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Timestamp paidDate) {
        this.paidDate = paidDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Double getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Double maxLimit) {
        this.maxLimit = maxLimit;
    }
    
}
