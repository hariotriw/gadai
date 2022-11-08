package com.nds.gadai.models;

import java.math.BigDecimal;

public class ProductModel extends RecordModel{

    private Integer id;
    private String type;
    private String name;
    private String description;
    private BigDecimal ltv;
    private Integer tenor;
    private String adminOpeningType;
    private BigDecimal adminOpeningCost;
    private String adminClosingType;
    private BigDecimal adminClosingCost;
    private BigDecimal savingServicePercent;
    private Integer savingServiceNumeric;
    private BigDecimal penaltyBillPercent;
    private Integer penaltyBillNumerik;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getLtv() {
        return ltv;
    }
    public void setLtv(BigDecimal ltv) {
        this.ltv = ltv;
    }
    public Integer getTenor() {
        return tenor;
    }
    public void setTenor(Integer tenor) {
        this.tenor = tenor;
    }
    public String getAdminOpeningType() {
        return adminOpeningType;
    }
    public void setAdminOpeningType(String adminOpeningType) {
        this.adminOpeningType = adminOpeningType;
    }
    public BigDecimal getAdminOpeningCost() {
        return adminOpeningCost;
    }
    public void setAdminOpeningCost(BigDecimal adminOpeningCost) {
        this.adminOpeningCost = adminOpeningCost;
    }
    public String getAdminClosingType() {
        return adminClosingType;
    }
    public void setAdminClosingType(String adminClosingType) {
        this.adminClosingType = adminClosingType;
    }
    public BigDecimal getAdminClosingCost() {
        return adminClosingCost;
    }
    public void setAdminClosingCost(BigDecimal adminClosingCost) {
        this.adminClosingCost = adminClosingCost;
    }
    public BigDecimal getSavingServicePercent() {
        return savingServicePercent;
    }
    public void setSavingServicePercent(BigDecimal savingServicePercent) {
        this.savingServicePercent = savingServicePercent;
    }
    public Integer getSavingServiceNumeric() {
        return savingServiceNumeric;
    }
    public void setSavingServiceNumeric(Integer savingServiceNumeric) {
        this.savingServiceNumeric = savingServiceNumeric;
    }
    public BigDecimal getPenaltyBillPercent() {
        return penaltyBillPercent;
    }
    public void setPenaltyBillPercent(BigDecimal penaltyBillPercent) {
        this.penaltyBillPercent = penaltyBillPercent;
    }
    public Integer getPenaltyBillNumerik() {
        return penaltyBillNumerik;
    }
    public void setPenaltyBillNumerik(Integer penaltyBillNumerik) {
        this.penaltyBillNumerik = penaltyBillNumerik;
    }
    
}
