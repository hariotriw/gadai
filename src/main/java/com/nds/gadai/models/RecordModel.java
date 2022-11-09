package com.nds.gadai.models;

import javax.validation.constraints.NotNull;

public class RecordModel {
    // record
    @NotNull(message = "User id is required")
    private String actorId;
    private String recStatus;
    
    public String getActorId() {
        return actorId;
    }
    public void setActorId(String actorId) {
        this.actorId = actorId;
    }
    public String getRecStatus() {
        return recStatus;
    }
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }
}
