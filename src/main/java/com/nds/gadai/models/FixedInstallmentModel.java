package com.nds.gadai.models;

import javax.validation.constraints.Pattern;

public class FixedInstallmentModel extends RecordModel{

    private String transactionNumber;
    private String customerId;
    private String customerName;

    private String customerIdNumber;
    @Pattern(regexp = "((19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "Trx date format must be yyyy-MM-dd")
    private String trxDateBefore;

    @Pattern(regexp = "((19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "Trx date format must be yyyy-MM-dd")
    private String trxDateAfter;

    private String productId;
    private String productName;
    private String productDesc;  
    
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
    public String getCustomerIdNumber() {
        return customerIdNumber;
    }
    public void setCustomerIdNumber(String customerIdNumber) {
        this.customerIdNumber = customerIdNumber;
    }
    public String getTrxDateBefore() {
        return trxDateBefore;
    }
    public void setTrxDateBefore(String trxDateBefore) {
        this.trxDateBefore = trxDateBefore;
    }
    public String getTrxDateAfter() {
        return trxDateAfter;
    }
    public void setTrxDateAfter(String trxDateAfter) {
        this.trxDateAfter = trxDateAfter;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
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
    
}
