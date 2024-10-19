package be.kdg.prog6.warehouse.adapters.in.dtos;

import be.kdg.prog6.common.events.WarehouseActivityType;

import java.time.LocalDateTime;

public class WarehouseActivityDto {
    private WarehouseActivityType action;
    private double amountTons;
    private LocalDateTime activityDate;

    public WarehouseActivityDto(WarehouseActivityType action, double amountTons, LocalDateTime activityDate) {
        this.action = action;
        this.amountTons = amountTons;
        this.activityDate = activityDate;
    }

    public WarehouseActivityType getAction() {
        return action;
    }

    public double getAmountTons() {
        return amountTons;
    }

    public LocalDateTime getActivityDate() {
        return activityDate;
    }
}
