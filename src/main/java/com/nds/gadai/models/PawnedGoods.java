package com.nds.gadai.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

public class PawnedGoods extends RecordModel{
    @NotEmpty
    private Integer id;

    @NotEmpty
    private String transactionNumber;

    @NotEmpty
    private String goodsName;

    private String description;

    @NotEmpty
    private Integer amount;

    @NotEmpty
    private BigDecimal pricePerUnit;
    
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
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

}
