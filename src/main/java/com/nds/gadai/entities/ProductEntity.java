package com.nds.gadai.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name = "ms_product")
public class ProductEntity {
    @Id
    @Pattern(regexp = "^(PRD)\\d{3}", message = "Product Id starts with PRD and contains six character")
    private String id;

    @OneToMany(targetEntity = FixedInstallmentEntity.class, mappedBy = "product")
    private List<FixedInstallmentEntity> fixedInstallments;

    @Column(name = "type")
    @NotEmpty(message = "Product type is required")
    private String type;
    
    @Column(name = "name")
    @NotEmpty(message = "Product name is required")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ltv")
    @NotNull(message = "ltv is required")
    private Double ltv;

    @Column(name = "tenor")
    @NotNull(message = "tenor is required")
    private Integer tenor;

    @Column(name = "admin_opening_type")
    @NotNull(message = "admin opening payment type is required")
    private String adminOpeningType;
    
    @Column(name = "admin_opening_cost")
    @NotNull(message = "admin opening cost is required")
    private Double adminOpeningCost;

    @Column(name = "admin_closing_type")
    @NotNull(message = "admin closing payment type is required")
    private String adminClosingType;

    @Column(name = "admin_closing_cost")
    @NotNull(message = "admin closing cost is required")
    private Double adminClosingCost;

    @Column(name = "saving_service_percent")
    @NotNull(message = "saving service percentage is required")
    private Double savingServicePercent;

    @Column(name = "saving_service_numeric")
    @NotNull(message = "saving service numeric is required")
    private Integer savingServiceNumeric;

    @Column(name = "penalty_bill_percent")
    @NotNull(message = "penalty bill percentage is required")
    private Double penaltyBillPercent;

    @Column(name = "penalty_bill_numeric")
    @NotNull(message = "penalty bill numeric is required")
    private Integer penaltyBillNumeric;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "updater_id")
    private String updaterId;

    @Column(name = "deleter_id")
    private String deleterId;

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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getDeleterId() {
        return deleterId;
    }

    public void setDeleterId(String deleterId) {
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
