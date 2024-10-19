package be.kdg.prog6.landside.adapters.out.db.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(catalog="landside", name="warehouses")
public class WarehouseJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="warehouse_id")
    private UUID id;

    @Column(name="seller_id", nullable = true)
    UUID sellerId;

    @Column(name="material_id", nullable = true)
    UUID materialId;

    @Column(name="current_tons")
    double currentTons;

    @Column(name="x_coord")
    double xCoord;

    @Column(name="y_coord")
    double yCoord;





    public WarehouseJPAEntity() {
    }

    public WarehouseJPAEntity(UUID id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "WarehouseJPAEntity{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", materialId=" + materialId +
                ", currentTons=" + currentTons +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public UUID getMaterialId() {
        return materialId;
    }

    public void setMaterialId(UUID materialId) {
        this.materialId = materialId;
    }

    public double getCurrentTons() {
        return currentTons;
    }

    public void setCurrentTons(double currentTons) {
        this.currentTons = currentTons;
    }

    public double getXCoord() {
        return xCoord;
    }

    public void setXCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public double getYCoord() {
        return yCoord;
    }

    public void setYCoord(double yCoord) {
        this.yCoord = yCoord;
    }
}
