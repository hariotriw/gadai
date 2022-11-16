package com.nds.gadai.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tx_installment")
public class InstallmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "transaction_number", referencedColumnName = "transaction_number")
    @Column(name = "transaction_number")
    private String transactionNumber;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
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
