package be.kdg.prog6.landside.adapters.in.web.dtos;

import be.kdg.prog6.common.domain.Material;

import java.util.UUID;

public class WarehouseProjectionDto {

    private UUID id;
    private double amountTons;

    private double xCoord;

    private double yCoord;
    private double widthMeters;

    private double lengthMeters;

    private double maxCapacityTons;

    private Material material;

    private UUID sellerId;
    private String sellerName;

    public WarehouseProjectionDto(UUID id, double amountTons, double xCoord, double yCoord, double widthMeters, double lengthMeters, double maxCapacityTons, Material material, UUID sellerId, String sellerName) {
        this.id = id;
        this.amountTons = amountTons;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.widthMeters = widthMeters;
        this.lengthMeters = lengthMeters;
        this.maxCapacityTons = maxCapacityTons;
        this.material = material;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
    }

    public WarehouseProjectionDto() {
    }

    public UUID getId() {
        return id;
    }

    public double getAmountTons() {
        return amountTons;
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public double getWidthMeters() {
        return widthMeters;
    }

    public double getLengthMeters() {
        return lengthMeters;
    }

    public double getMaxCapacityTons() {
        return maxCapacityTons;
    }

    public Material getMaterial() {
        return material;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
