package com.nds.gadai.models;

import javax.validation.constraints.NotEmpty;

public class RecordModel {
    // record
    @NotEmpty
    private String actorId;

    @NotEmpty
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
