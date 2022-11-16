package com.nds.gadai.models;

import java.math.BigDecimal;
import java.security.Timestamp;

import javax.validation.constraints.NotEmpty;

public class InstallmentModel extends RecordModel {
    @NotEmpty
    private Integer id;

    @NotEmpty
    private String transactionNumber;

    @NotEmpty
    private Integer installmentTo;

    @NotEmpty
    private BigDecimal baseInstallment;

    @NotEmpty
    private BigDecimal savingServiceCost;

    @NotEmpty
    private BigDecimal latePenalty;

    @NotEmpty
    private BigDecimal totalInstallment;

    @NotEmpty
    private BigDecimal installmentStatus;

    private Timestamp activeInstallmentDate;
    
    private Timestamp paidDate;
    
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
    public BigDecimal getInstallmentStatus() {
        return installmentStatus;
    }
    public void setInstallmentStatus(BigDecimal installmentStatus) {
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
    

}
