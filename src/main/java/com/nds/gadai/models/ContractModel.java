package com.nds.gadai.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ContractModel extends RecordModel{
    @NotEmpty(message = "product id is required")
    @Pattern(regexp = "^(PRD)\\d{3}", message = "Product Id starts with PRD and contains six character")
    private String productId;
    @NotEmpty(message = "customer id is required")
    @Pattern(regexp = "^\\d{10}$", message = "Customer Id contains 10 digits")
    private String customerId;

    @NotNull(message = "total estimated value is required")
    private BigDecimal totalEstimatedValue;

    private BigDecimal ltv;
    private BigDecimal maxLoan;

    @NotNull(message = "liquidation value is required")
    private BigDecimal custLiquidationValue;

    private BigDecimal adminOpeningCost;
    private BigDecimal adminClosingCost;

    @NotNull(message = "admin discout is required")
    @Min(value = 0, message = "Percentage must be between 0 to 100%")
    @Max(value = 100, message = "Percentage must be between 0 to 100%")
    private BigDecimal adminDiscount;

    private BigDecimal adminOpeningCostFinal;
    private BigDecimal totalLoan;
    private Timestamp transactionDate;
    private Timestamp deadlineDate;
    private BigDecimal savingServicePercent;
    private BigDecimal savingServiceCost;
    private BigDecimal savingServiceTotal;
    private BigDecimal totalRepayment;
    private List<PawnedGoods> pawnedGoods;
    
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getLtv() {
        return ltv;
    }
    public void setLtv(BigDecimal ltv) {
        this.ltv = ltv;
    }

    public BigDecimal getMaxLoan() {
        return maxLoan;
    }
    public void setMaxLoan(BigDecimal maxLoan) {
        this.maxLoan = maxLoan;
    }

    public BigDecimal getTotalEstimatedValue() {
        return totalEstimatedValue;
    }
    public void setTotalEstimatedValue(BigDecimal totalEstimatedValue) {
        this.totalEstimatedValue = totalEstimatedValue;
    }
    public BigDecimal getCustLiquidationValue() {
        return custLiquidationValue;
    }
    public void setCustLiquidationValue(BigDecimal custLiquidationValue) {
        this.custLiquidationValue = custLiquidationValue;
    }
    public BigDecimal getAdminDiscount() {
        return adminDiscount;
    }
    public void setAdminDiscount(BigDecimal adminDiscount) {
        this.adminDiscount = adminDiscount;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public List<PawnedGoods> getPawnedGoods() {
        return pawnedGoods;
    }
    public void setPawnedGoods(List<PawnedGoods> pawnedGoods) {
        this.pawnedGoods = pawnedGoods;
    }
    public BigDecimal getAdminOpeningCost() {
        return adminOpeningCost;
    }
    public void setAdminOpeningCost(BigDecimal adminOpeningCost) {
        this.adminOpeningCost = adminOpeningCost;
    }
    public BigDecimal getAdminClosingCost() {
        return adminClosingCost;
    }
    public void setAdminClosingCost(BigDecimal adminClosingCost) {
        this.adminClosingCost = adminClosingCost;
    }
    public BigDecimal getAdminOpeningCostFinal() {
        return adminOpeningCostFinal;
    }
    public void setAdminOpeningCostFinal(BigDecimal adminOpeningCostFinal) {
        this.adminOpeningCostFinal = adminOpeningCostFinal;
    }
    public BigDecimal getTotalLoan() {
        return totalLoan;
    }
    public void setTotalLoan(BigDecimal totalLoan) {
        this.totalLoan = totalLoan;
    }
    public Timestamp getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
    public Timestamp getDeadlineDate() {
        return deadlineDate;
    }
    public void setDeadlineDate(Timestamp deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
    public BigDecimal getSavingServicePercent() {
        return savingServicePercent;
    }
    public void setSavingServicePercent(BigDecimal savingServicePercent) {
        this.savingServicePercent = savingServicePercent;
    }
    public BigDecimal getSavingServiceCost() {
        return savingServiceCost;
    }
    public void setSavingServiceCost(BigDecimal savingServiceCost) {
        this.savingServiceCost = savingServiceCost;
    }
    public BigDecimal getSavingServiceTotal() {
        return savingServiceTotal;
    }
    public void setSavingServiceTotal(BigDecimal savingServiceTotal) {
        this.savingServiceTotal = savingServiceTotal;
    }
    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }
    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
    }
}
