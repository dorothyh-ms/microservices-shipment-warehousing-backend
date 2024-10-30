package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.WarehouseActivityType;

import java.util.UUID;

public class Warehouse {

    public static final double MAX_CAPACITY_TONS = 500000;
    public static final double LENGTH_METERS = 80;

    public static final double WIDTH_METERS = 30;

    private UUID id;

    private UUID sellerId;

    private Material material;

    private double currentTons;

    private double xCoord;

    private double yCoord;

    public Warehouse(UUID id, UUID sellerId, Material material, double currentTons, double xCoord, double yCoord) {
        this.id = id;
        this.sellerId = sellerId;
        this.material = material;
        this.currentTons = currentTons;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Warehouse(UUID id, double amountTons){
        this.id = id;
        this.currentTons = amountTons;
    }

    public Warehouse(UUID sellerId) {
        this.sellerId = sellerId;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getCurrentTons() {
        return currentTons;
    }

    public void setCurrentTons(double currentTons) {
        this.currentTons = currentTons;
    }


    public boolean isAvailable(Material material){
        boolean materialMatchedOrNothingStored = (this.material == null) || (this.material.equals(material));
        return (currentTons < MAX_CAPACITY_TONS*0.8) && materialMatchedOrNothingStored;
    }


    public void changeCurrentAmountTons(double amountTons, WarehouseActivityType activityType) {
        switch(activityType){
            case DELIVERY -> this.currentTons += amountTons;
            case SHIPMENT -> this.currentTons -= amountTons;
        }
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

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", material=" + material +
                ", currentTons=" + currentTons +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }
}
