package com.nds.gadai.models;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class CustomerModel extends RecordModel {
    @NotEmpty(message = "Customer id is required")
    private String id;

    @NotEmpty(message = "Customer name is required")
    private String name;

    @NotEmpty(message = "Customer id number is required")
    private String idNumber;

    @Pattern(regexp = "^(62|0)8[1-9][0-9]{6,9}$",
    message = "Call number contains nine to twelve digits and starts with 0")
    private String phoneNumber;

    @NotEmpty(message = "Customer gender is required")
    @Pattern(regexp = "(P|W)", message = "Customer gender must be P (pria) or W (wanita)")
    private String gender;

    @NotEmpty(message = "Business Type is required")
    @Pattern(regexp = "[1-9]", message = "Must pick customer business type from number 1-9")
    private String businessType;

    @DecimalMin(value="1000000.00", message = "Limit minimum is 1000000")
    @DecimalMax(value="3000000.00", message = "Limit minimum is 3000000")
    private BigDecimal maxLimit;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public BigDecimal getMaxLimit() {
        return maxLimit;
    }
    public void setMaxLimit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }
}
