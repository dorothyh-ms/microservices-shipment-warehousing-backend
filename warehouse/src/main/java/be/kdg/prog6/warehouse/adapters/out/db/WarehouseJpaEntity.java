package be.kdg.prog6.warehouse.adapters.out.db;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog="warehouse", name="warehouses")
public class WarehouseJpaEntity {

    @Id
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name="seller_id", nullable = false)
    private SellerJpaEntity seller;

    @Column(name="base_amount_tons")
    private double baseAmountTons = 0;


    @Column(name="base_amount_tons_date", nullable = true)
    private LocalDateTime baseAmountTonsDate;


    @ManyToOne
    @JoinColumn(name="material_id", nullable=false)
    private MaterialJpaEntity material;

    public WarehouseJpaEntity(UUID uuid, SellerJpaEntity seller, MaterialJpaEntity material) {
        this.uuid = uuid;
        this.seller = seller;
        this.material = material;
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

    public MaterialJpaEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialJpaEntity material) {
        this.material = material;
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
