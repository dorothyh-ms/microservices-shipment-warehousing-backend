package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.events.WarehouseActivityType;

import java.util.UUID;

public class Warehouse {

    public static final double MAX_CAPACITY_TONS = 500000;
    public static final double LENGTH_METERS = 80;

    public static final double WIDTH_METERS = 30;

    private UUID id;

    private UUID sellerId;

    private UUID materialID;

    private double currentTons;

    private double xCoord;

    private double yCoord;

    public Warehouse(UUID id, UUID sellerId, UUID materialID, double currentTons, double xCoord, double yCoord) {
        this.id = id;
        this.sellerId = sellerId;
        this.materialID = materialID;
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

    public UUID getMaterialID() {
        return materialID;
    }

    public void setMaterialID(UUID materialID) {
        this.materialID = materialID;
    }

    public double getCurrentTons() {
        return currentTons;
    }

    public void setCurrentTons(double currentTons) {
        this.currentTons = currentTons;
    }


    public boolean isAvailable(UUID materialID){
        boolean materialMatchedOrNothingStored = (this.materialID == null) || (this.materialID.equals(materialID));
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
                ", materialID=" + materialID +
                ", currentTons=" + currentTons +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }
}
