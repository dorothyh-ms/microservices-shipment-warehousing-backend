package be.kdg.prog6.waterside.adapters.out.db.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog="waterside", name="inspection_operations")
public class InspectionOperationJpaEntity {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name="shipping_order_id")
    private ShippingOrderJpaEntity shippingOrder;

    @Column(name="inspection_date")
    private LocalDateTime inspectionDate;

    @Column(name="inspector_signature")
    private String inspectorSignature;

    public InspectionOperationJpaEntity() {
    }

    public InspectionOperationJpaEntity(UUID id, ShippingOrderJpaEntity shippingOrder, LocalDateTime inspectionDate, String inspectorSignature) {
        this.id = id;
        this.shippingOrder = shippingOrder;
        this.inspectionDate = inspectionDate;
        this.inspectorSignature = inspectorSignature;
    }

    public InspectionOperationJpaEntity(UUID id, ShippingOrderJpaEntity shippingOrder) {
        this.id = id;
        this.shippingOrder = shippingOrder;
    }

    public UUID getId() {
        return id;
    }

    public ShippingOrderJpaEntity getShippingOrder() {
        return shippingOrder;
    }

    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    public String getInspectorSignature() {
        return inspectorSignature;
    }

    public void setInspectionDate(LocalDateTime inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public void setInspectorSignature(String inspectorSignature) {
        this.inspectorSignature = inspectorSignature;
    }

    @Override
    public String toString() {
        return "InspectionOperationJpaEntity{" +
                "id=" + id +
                ", shippingOrder=" + shippingOrder +
                ", inspectionDate=" + inspectionDate +
                ", inspectorSignature='" + inspectorSignature + '\'' +
                '}';
    }
}
