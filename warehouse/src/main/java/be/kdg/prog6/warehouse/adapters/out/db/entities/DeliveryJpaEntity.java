package be.kdg.prog6.warehouse.adapters.out.db.entities;


import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.warehouse.domain.Warehouse;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog="warehouse", name="deliveries")
public class DeliveryJpaEntity {

    @Id
    private UUID id;

    @Column(name="delivered_datetime")
    private LocalDateTime deliveredDateTime;

    @Enumerated(EnumType.STRING)
    private Material material;

    @Column(name="amount_tons")
    private double amountTons;

    @ManyToOne
    @JoinColumn(name="warehouse_id", nullable=false)
    private WarehouseJpaEntity warehouse;

    public DeliveryJpaEntity(UUID id, LocalDateTime deliveredDateTime, Material material, double amountTons, WarehouseJpaEntity warehouse) {
        this.id = id;
        this.deliveredDateTime = deliveredDateTime;
        this.material = material;
        this.amountTons = amountTons;
        this.warehouse = warehouse;
    }

    public DeliveryJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDeliveredDateTime() {
        return deliveredDateTime;
    }

    public Material getMaterial() {
        return material;
    }

    public double getAmountTons() {
        return amountTons;
    }

}
