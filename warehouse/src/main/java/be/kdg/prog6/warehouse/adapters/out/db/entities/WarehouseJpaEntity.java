package be.kdg.prog6.warehouse.adapters.out.db.entities;

import be.kdg.prog6.common.domain.Material;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(catalog = "warehouse", name = "warehouses")
public class WarehouseJpaEntity {

    @Id
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SellerJpaEntity seller;

    @Column(name = "base_amount_tons")
    private double baseAmountTons = 0;


    @Column(name = "base_amount_tons_date", nullable = true)
    private LocalDateTime baseAmountTonsDate;


    @Enumerated(EnumType.STRING)
    private Material material;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DeliveryJpaEntity> deliveries;

    public WarehouseJpaEntity(UUID uuid, SellerJpaEntity seller, double baseAmountTons, LocalDateTime baseAmountTonsDate, Material material, List<DeliveryJpaEntity> deliveries) {
        this.uuid = uuid;
        this.seller = seller;
        this.baseAmountTons = baseAmountTons;
        this.baseAmountTonsDate = baseAmountTonsDate;
        this.material = material;
        this.deliveries = deliveries;
    }

    public WarehouseJpaEntity(UUID uuid) {
        this.uuid = uuid;
    }

    public WarehouseJpaEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public SellerJpaEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerJpaEntity seller) {
        this.seller = seller;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setDeliveries(List<DeliveryJpaEntity> deliveries) {
        this.deliveries = deliveries;
    }

    public List<DeliveryJpaEntity> getDeliveries() {
        return deliveries;
    }

    public double getBaseAmountTons() {
        return baseAmountTons;
    }

    public void setBaseAmountTons(double baseAmountTons) {
        this.baseAmountTons = baseAmountTons;
    }

    public LocalDateTime getBaseAmountTonsDate() {
        return baseAmountTonsDate;
    }

    public void setBaseAmountTonsDate(LocalDateTime baseAmountTonsDate) {
        this.baseAmountTonsDate = baseAmountTonsDate;
    }
}
