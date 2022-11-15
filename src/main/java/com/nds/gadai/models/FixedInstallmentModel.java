package com.nds.gadai.models;

import java.math.BigDecimal;
import java.security.Timestamp;

public class FixedInstallmentModel extends RecordModel{
    private String transactionNumber;
    private String customerId;
    private String customerName;
    private Timestamp transferDate;
    private Integer productId;
    private String productName;
    private String productDesc;  
    private BigDecimal customerLoan;
    
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
    
    public BigDecimal getCustomerLoan() {
        return customerLoan;
    }
    public void setCustomerLoan(BigDecimal customerLoan) {
        this.customerLoan = customerLoan;
    }
    
}
