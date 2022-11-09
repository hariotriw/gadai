package com.nds.gadai.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
@Table(name = "tx_payment")
public class PaymentEntity {

    @Id
    @GenericGenerator(name = "payment_number_seq", strategy = "com.nds.gadai.generators.PaymentNumberGenerator")
    @GeneratedValue(generator = "payment_number_seq")
    @Column(name = "payment_number")
    private String paymentNumber;

    @OneToMany(targetEntity = FixedInstallmentEntity.class, mappedBy = "transactionNumber")
    private List<FixedInstallmentEntity> transactionNumbers;
    
    @JoinColumn(name = "transaction_number", referencedColumnName = "transaction_number")
    @Column(name = "transaction_number")
    private String transactionNumber;
    
    @Column(name = "total_installment_cost")
    private BigDecimal totalInstallmentCost;

    @Column(name = "total_installment_type")
    private BigDecimal totalInstallmentPenalty;

    @Column(name = "admin_closing_cost")
    private BigDecimal adminClosingCost;
    
    @Column(name = "total_bill")
    private BigDecimal totalBill;

    @Column(name = "rounding_up")
    private BigDecimal roundingUp;
    
    @Column(name = "total_payment")
    private BigDecimal totalPayment;
    
    @Column(name = "payment_method")
    private String paymentMethod;

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

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public BigDecimal getTotalInstallmentCost() {
        return totalInstallmentCost;
    }

    public void setTotalInstallmentCost(BigDecimal totalInstallmentCost) {
        this.totalInstallmentCost = totalInstallmentCost;
    }

    public BigDecimal getTotalInstallmentPenalty() {
        return totalInstallmentPenalty;
    }

    public void setTotalInstallmentPenalty(BigDecimal totalInstallmentPenalty) {
        this.totalInstallmentPenalty = totalInstallmentPenalty;
    }

    public BigDecimal getAdminClosingCost() {
        return adminClosingCost;
    }

    public void setAdminClosingCost(BigDecimal adminClosingCost) {
        this.adminClosingCost = adminClosingCost;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    public BigDecimal getRoundingUp() {
        return roundingUp;
    }

    public void setRoundingUp(BigDecimal roundingUp) {
        this.roundingUp = roundingUp;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
