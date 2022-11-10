package com.nds.gadai.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tx_fixed_installment")
public class FixedInstallmentEntity {
    
    @Id
    @GenericGenerator(name = "transaction_number_seq", strategy = "com.nds.gadai.generators.TransactionNumberGenerator")
    @GeneratedValue(generator = "transaction_number_seq")
    @JoinColumn(name = "transaction_number", referencedColumnName = "transaction_number")
    @Column(name = "transaction_number")
    private String transactionNumber;

    @OneToMany(targetEntity = InstallmentEntity.class, mappedBy = "transactionNumber")
    private List<InstallmentEntity> installments;

    @OneToMany(targetEntity = PawnedGoodsEntity.class, mappedBy = "transactionNumber")
    private List<PawnedGoodsEntity> pawnedGoods;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "customer_id", referencedColumnName = "id"),
        @JoinColumn(name = "customer_name", referencedColumnName = "name"),
        @JoinColumn(name = "customer_id_number", referencedColumnName = "id_number")
    })
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "product_id", referencedColumnName = "id"),
        @JoinColumn(name = "product_name", referencedColumnName = "name"),
        @JoinColumn(name = "product_desc", referencedColumnName = "description")
    })
    private ProductEntity product;

    @Column(name = "transfer_date")
    private Timestamp transferDate;

    @Column(name = "creator_id")
    private String creatorId;

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public List<InstallmentEntity> getInstallments() {
        return installments;
    }

    public void setInstallments(List<InstallmentEntity> installments) {
        this.installments = installments;
    }

    public List<PawnedGoodsEntity> getPawnedGoods() {
        return pawnedGoods;
    }

    public void setPawnedGoods(List<PawnedGoodsEntity> pawnedGoods) {
        this.pawnedGoods = pawnedGoods;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Timestamp getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Timestamp transferDate) {
        this.transferDate = transferDate;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
