package be.kdg.prog6.warehouse.adapters.out.db.entities;

import be.kdg.prog6.warehouse.domain.PurchaseOrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(catalog = "warehouse", name="purchase_orders")
public class PurchaseOrderJpaEntity {
    @Id
    private UUID id;

    @Column(name="order_date")
    private LocalDateTime orderDate;

    @Column(name="purchase_order_number")
    private String purchaseOrderNumber;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private SellerJpaEntity seller;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus status;

    private String vesselNumber;

    private String buyerEnterpriseNumber;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PurchaseOrderLineJpaEntity> orderLines = new ArrayList<>();



    public PurchaseOrderJpaEntity(UUID id,String purchaseOrderNumber, LocalDateTime orderDate, SellerJpaEntity seller, PurchaseOrderStatus status, String vesselNumber, String buyerEnterpriseNumber) {
        this.id = id;
        this.purchaseOrderNumber= purchaseOrderNumber;
        this.orderDate = orderDate;
        this.seller = seller;
        this.status = status;
        this.vesselNumber = vesselNumber;
        this.buyerEnterpriseNumber = buyerEnterpriseNumber;
    }

    public PurchaseOrderJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public SellerJpaEntity getSeller() {
        return seller;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public String getBuyerEnterpriseNumber() {
        return buyerEnterpriseNumber;
    }

    public void setBuyerEnterpriseNumber(String buyerEnterpriseNumber) {
        this.buyerEnterpriseNumber = buyerEnterpriseNumber;
    }

    public void setOrderLines(List<PurchaseOrderLineJpaEntity> orderLines) {
        this.orderLines = orderLines;
    }

    public List<PurchaseOrderLineJpaEntity> getOrderLines() {
        return orderLines;
    }
}
