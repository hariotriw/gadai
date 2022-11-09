package com.nds.gadai.models;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ProductModel extends RecordModel{
    
    @Pattern(regexp = "^(PRD)\\d{3}", message = "Product Id starts with PRD and contains six character")
    private String id;

    @NotEmpty(message = "Product type is required")
    @Pattern(regexp = "[1-2]", message = "Must pick product type from number 1-2")
    private String type;

    @NotEmpty(message = "Product name is required")
    private String name;

    private String description;

    @NotEmpty(message = "Loan to Value is required")
    @Min(value = 0, message = "Percentage must be between 0 to 100")
    @Max(value = 100, message = "Percentage must be between 0 to 100")
    private BigDecimal ltv;

    @NotEmpty(message = "Ltv range is required")
    @Min(value = 0, message = "Percentage must be between 0 to 100")
    @Max(value = 100, message = "Percentage must be between 0 to 100")
    private BigDecimal ltvLow;

    @NotEmpty(message = "Ltv range is required")
    @Min(value = 0, message = "Percentage must be between 0 to 100")
    @Max(value = 100, message = "Percentage must be between 0 to 100")
    private BigDecimal ltvHigh;

    @NotEmpty(message = "Tenor is required")
    private Integer tenor;

    @NotEmpty(message = "Admin Opening Type is required")
    @Pattern(regexp = "[1-2]", message = "Must pick product type from number 1-2")
    private String adminOpeningType;

    @NotEmpty(message = "Admin Opening Cost is required")
    private BigDecimal adminOpeningCost;

    @NotEmpty(message = "Admin Closing Type is required")
    @Pattern(regexp = "[1-2]", message = "Must pick product type from number 1-2")
    private String adminClosingType;

    @NotEmpty(message = "Admin Closing Cost is required")
    private BigDecimal adminClosingCost;

    @NotEmpty(message = "Saving service percent is required")
    private BigDecimal savingServicePercent;

    @NotEmpty(message = "Saving service range is required")
    @Min(value = 0, message = "Percentage must be between 0 to 100")
    @Max(value = 100, message = "Percentage must be between 0 to 100")
    private BigDecimal savingServicePercentLow;

    @NotEmpty(message = "Saving service range is required")
    @Min(value = 0, message = "Percentage must be between 0 to 100")
    @Max(value = 100, message = "Percentage must be between 0 to 100")
    private BigDecimal savingServicePercentHigh;

    @NotEmpty(message = "Saving service numeric is required")
    private Integer savingServiceNumeric;

    @NotEmpty(message = "penalty bill percent is required")
    private BigDecimal penaltyBillPercent;
    
    @NotEmpty(message = "penalty bill numeric is required")
    private Integer penaltyBillNumeric;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public BigDecimal getLtvLow() {
        return ltvLow;
    }

    public void setLtvLow(BigDecimal ltvLow) {
        this.ltvLow = ltvLow;
    }

    public BigDecimal getLtvHigh() {
        return ltvHigh;
    }

    public void setLtvHigh(BigDecimal ltvHigh) {
        this.ltvHigh = ltvHigh;
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

    public BigDecimal getSavingServicePercentLow() {
        return savingServicePercentLow;
    }

    public void setSavingServicePercentLow(BigDecimal savingServicePercentLow) {
        this.savingServicePercentLow = savingServicePercentLow;
    }

    public BigDecimal getSavingServicePercentHigh() {
        return savingServicePercentHigh;
    }

    public void setSavingServicePercentHigh(BigDecimal savingServicePercentHigh) {
        this.savingServicePercentHigh = savingServicePercentHigh;
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

    public Integer getPenaltyBillNumeric() {
        return penaltyBillNumeric;
    }

    public void setPenaltyBillNumeric(Integer penaltyBillNumeric) {
        this.penaltyBillNumeric = penaltyBillNumeric;
    }
}
