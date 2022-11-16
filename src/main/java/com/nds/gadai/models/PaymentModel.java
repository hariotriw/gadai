package com.nds.gadai.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

public class PaymentModel extends RecordModel {
    @NotEmpty
    private String paymentNumber;

    @NotEmpty
    private String transactionNumber;

    @NotEmpty
    private BigDecimal totalInstallmentCost;

    @NotEmpty
    private BigDecimal totalInstallmentPenalty;

    @NotEmpty
    private BigDecimal adminClosingCost;

    @NotEmpty
    private BigDecimal totalBill;

    @NotEmpty
    private BigDecimal roundingUp;

    @NotEmpty
    private BigDecimal totalPayment;

    @NotEmpty
    private String paymentMethod;
    
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
}
