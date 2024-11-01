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




    private Material material;

    private double amountTons;
    @ManyToOne
    @JoinColumn(name="purchase_order_id", nullable=false)
    private PurchaseOrderJpaEntity purchaseOrder;

    public PurchaseOrderLineJpaEntity() {
    }

    public PurchaseOrderLineJpaEntity(Material material, double amountTons, PurchaseOrderJpaEntity purchaseOrder) {
        this.material = material;
        this.amountTons = amountTons;
        this.purchaseOrder = purchaseOrder;
    }

    public Material getMaterial() {
        return material;
    }

    public double getAmountTons() {
        return amountTons;
    }
}
