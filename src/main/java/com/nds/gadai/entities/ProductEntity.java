package com.nds.gadai.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "ms_product")
public class ProductEntity {
    @Id
    private String id;

    @Column(name = "type")
    private String type;
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ltv")
    private Double ltv;

    @Column(name = "tenor")
    private Integer tenor;

    @Column(name = "admin_opening_type")
    private String adminOpeningType;
    
    @Column(name = "admin_opening_cost")
    private Double adminOpeningCost;

    @Column(name = "admin_closing_type")
    private String adminClosingType;

    @Column(name = "admin_closing_cost")
    private Double adminClosingCost;

    @Column(name = "saving_service_percent")
    private Double savingServicePercent;

    @Column(name = "saving_service_numeric")
    private Integer savingServiceNumeric;

    @Column(name = "penalty_bill_percent")
    private Double penaltyBillPercent;

    @Column(name = "penalty_bill_numeric")
    private Integer penaltyBillNumeric;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "updater_id")
    private Integer updaterId;

    @Column(name = "deleter_id")
    private Integer deleterId;

    @Column(name = "rec_status")
    private String recStatus;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Integer getDeleterId() {
        return deleterId;
    }

    public void setDeleterId(Integer deleterId) {
        this.deleterId = deleterId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

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

    public Double getLtv() {
        return ltv;
    }

    public void setLtv(Double ltv) {
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

    public Double getAdminOpeningCost() {
        return adminOpeningCost;
    }

    public void setAdminOpeningCost(Double adminOpeningCost) {
        this.adminOpeningCost = adminOpeningCost;
    }

    public String getAdminClosingType() {
        return adminClosingType;
    }

    public void setAdminClosingType(String adminClosingType) {
        this.adminClosingType = adminClosingType;
    }

    public Double getAdminClosingCost() {
        return adminClosingCost;
    }

    public void setAdminClosingCost(Double adminClosingCost) {
        this.adminClosingCost = adminClosingCost;
    }

    public Double getSavingServicePercent() {
        return savingServicePercent;
    }

    public void setSavingServicePercent(Double savingServicePercent) {
        this.savingServicePercent = savingServicePercent;
    }

    public Integer getSavingServiceNumeric() {
        return savingServiceNumeric;
    }

    public void setSavingServiceNumeric(Integer savingServiceNumeric) {
        this.savingServiceNumeric = savingServiceNumeric;
    }

    public Double getPenaltyBillPercent() {
        return penaltyBillPercent;
    }

    public void setPenaltyBillPercent(Double penaltyBillPercent) {
        this.penaltyBillPercent = penaltyBillPercent;
    }

    public Integer getPenaltyBillNumeric() {
        return penaltyBillNumeric;
    }

    public void setPenaltyBillNumeric(Integer penaltyBillNumeric) {
        this.penaltyBillNumeric = penaltyBillNumeric;
    }
}
