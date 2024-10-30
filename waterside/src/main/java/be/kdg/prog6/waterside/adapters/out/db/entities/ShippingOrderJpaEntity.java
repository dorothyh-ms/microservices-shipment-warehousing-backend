package be.kdg.prog6.waterside.adapters.out.db.entities;

import be.kdg.prog6.waterside.domain.ShippingOrderStatus;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(catalog="waterside", name="shipping_orders")
public class ShippingOrderJpaEntity {
    @Id
    private UUID id;

    @Column(name="purchase_order_number", unique = true)
    private String purchaseOrderNo;

    @Column(name="shipping_order_number", unique = true)
    private String shippingOrderNo;


    @Column(name="vessel_number")
    private String vesselNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private ShippingOrderStatus shippingOrderStatus;

    public ShippingOrderJpaEntity(UUID id, String purchaseOrderNo, String shippingOrderNo, String vesselNumber, ShippingOrderStatus shippingOrderStatus) {
        this.id = id;
        this.purchaseOrderNo = purchaseOrderNo;
        this.shippingOrderNo = shippingOrderNo;
        this.vesselNumber = vesselNumber;
        this.shippingOrderStatus = shippingOrderStatus;
    }

    public ShippingOrderJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public ShippingOrderStatus getShippingOrderStatus() {
        return shippingOrderStatus;
    }

    @Override
    public String toString() {
        return "ShippingOrderJpaEntity{" +
                "id=" + id +
                ", purchaseOrderNo='" + purchaseOrderNo + '\'' +
                ", shippingOrderNo='" + shippingOrderNo + '\'' +
                ", vesselNumber='" + vesselNumber + '\'' +
                ", shippingOrderStatus=" + shippingOrderStatus +
                '}';
    }
}