package com.nds.gadai.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserModel extends RecordModel{

    private String id;
    private String name;
    private String phoneNumber;
    private BigDecimal maxLimit;
    private String description;
    private Timestamp regDate;
    
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public BigDecimal getMaxLimit() {
        return maxLimit;
    }
    public void setMax_limit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }
    public Timestamp getRegDate() {
        return regDate;
    }
    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
