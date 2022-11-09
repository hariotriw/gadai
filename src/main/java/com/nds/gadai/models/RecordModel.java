package com.nds.gadai.models;

import javax.validation.constraints.NotEmpty;

public class RecordModel {
    // record
    @NotEmpty(message = "User id is required")
    private Integer actorId;
    private String recStatus;
    
    public Integer getActorId() {
        return actorId;
    }
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
    public String getRecStatus() {
        return recStatus;
    }
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }
}
