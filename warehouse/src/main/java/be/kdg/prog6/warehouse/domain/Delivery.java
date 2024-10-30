package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.Material;

import java.time.LocalDateTime;
import java.util.UUID;

public class Delivery {

    private UUID id;
    private LocalDateTime deliveredDateTime;
    private Material material;
    private double amountTons;

    public Delivery(UUID id, LocalDateTime deliveredDateTime, Material material, double amountTons) {
        this.id = id;
        this.deliveredDateTime = deliveredDateTime;
        this.material = material;
        this.amountTons = amountTons;
    }

    public double removeAmount(double amountToRemove) {
        if (amountToRemove <= amountTons){
            this.amountTons -= amountToRemove;
            return 0;
        }
        this.amountTons = 0;
        return amountToRemove - this.amountTons;
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

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", deliveredDateTime=" + deliveredDateTime +
                ", material=" + material +
                ", amountTons=" + amountTons +
                '}';
    }
}
