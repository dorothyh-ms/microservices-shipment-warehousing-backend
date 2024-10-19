package be.kdg.prog6.landside.adapters.in.dtos;

import java.util.UUID;

public class WarehouseProjectionDto {

    private UUID id;
    private double amountTons;

    private double xCoord;

    private double yCoord;
    private double widthMeters;

    private double lengthMeters;

    private double maxCapacityTons;

    public WarehouseProjectionDto(UUID id, double amountTons, double xCoord, double yCoord, double widthMeters, double lengthMeters, double maxCapacityTons) {
        this.id = id;
        this.amountTons = amountTons;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.widthMeters = widthMeters;
        this.lengthMeters = lengthMeters;
        this.maxCapacityTons = maxCapacityTons;
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
}
