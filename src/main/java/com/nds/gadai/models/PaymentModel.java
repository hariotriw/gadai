package com.nds.gadai.models;

import java.math.BigDecimal;

public class PaymentModel extends RecordModel {
    private String paymentNumber;
    private String transactionNumber;
    private BigDecimal totalInstallmentCost;
    private BigDecimal totalInstallmentPenalty;
    private BigDecimal adminClosingCost;
    private BigDecimal totalBill;
    private BigDecimal roundingUp;
    private BigDecimal totalPayment;
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
