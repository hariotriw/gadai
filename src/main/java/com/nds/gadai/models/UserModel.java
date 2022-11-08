package com.nds.gadai.models;

import java.math.BigDecimal;

public class UserModel extends RecordModel{

    private String id;
    private String name;
    private String phoneNumber;
    private BigDecimal max_limit;
    private String description;
    
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
    public BigDecimal getMax_limit() {
        return max_limit;
    }
    public void setMax_limit(BigDecimal max_limit) {
        this.max_limit = max_limit;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
