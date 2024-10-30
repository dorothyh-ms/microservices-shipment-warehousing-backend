package be.kdg.prog6.warehouse.adapters.out.db.entities;

import be.kdg.prog6.common.domain.Material;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(catalog = "warehouse", name="purchase_order_lines")
public class PurchaseOrderLineJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="purchase_order_id", nullable=false)
    private PurchaseOrderJpaEntity purchaseOrder;


    private Material material;

    private double amountTons;

    public PurchaseOrderLineJpaEntity() {
    }

    public PurchaseOrderLineJpaEntity(PurchaseOrderJpaEntity purchaseOrder, Material material, double amountTons) {
        this.purchaseOrder = purchaseOrder;
        this.material = material;
        this.amountTons = amountTons;
    }

    public PurchaseOrderJpaEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public Material getMaterial() {
        return material;
    }

    public double getAmountTons() {
        return amountTons;
    }
}
