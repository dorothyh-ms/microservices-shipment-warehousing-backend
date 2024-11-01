package be.kdg.prog6.warehouse.domain;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.WarehouseActivityType;
import be.kdg.prog6.warehouse.exceptions.IncorrectMaterialException;

import main.java.be.kdg.prog6.warehouse.domain.Seller;
import org.springframework.cglib.core.Local;

public class Warehouse {

    private UUID id;
    private Seller seller;
    private Material material;

    private WarehouseActivityWindow activityWindow;

    private double baseAmountTons;

    private LocalDateTime baseAmountTonsDate;

    private List<Delivery> deliveries;


    public Warehouse(Seller seller, Material material, WarehouseActivityWindow activityWindow, List<Delivery> deliveries) {
        this.id = UUID.randomUUID();
        this.seller = seller;
        this.material = material;
        this.activityWindow = activityWindow;
        this.deliveries = deliveries;
    }

    public Warehouse(UUID id, Seller seller, Material material, WarehouseActivityWindow activityWindow, List<Delivery> deliveries) {
        this.id = id;
        this.seller = seller;
        this.material = material;
        this.activityWindow = activityWindow;
        this.deliveries = deliveries;
    }


    public WarehouseActivity addActivity(WarehouseActivity activity) {
        if (canStoreMaterial(activity.material())) {
            this.activityWindow.addActivity(activity);
            if (activity.action() == WarehouseActivityType.DELIVERY) {
                Delivery delivery = new Delivery(UUID.randomUUID(), activity.activityDate(), activity.material(), activity.amountTons());
                deliveries.add(delivery);
            } else {
                removeAmountFromStockpile(activity.amountTons());
            }
            return activity;
        } else {
            throw new IncorrectMaterialException();
        }
    }

    private void removeAmountFromStockpile(double amountTons) {
        double amountRemaining = amountTons;
        while (amountRemaining > 0) {
            var delivery = deliveries.stream()
                    .min(Comparator.comparing(Delivery::getDeliveredDateTime))
                    .orElse(null);
            if (delivery != null) {
                amountRemaining = delivery.removeAmount(amountRemaining);
//                if (delivery.getAmountTons() == 0){
//                    deliveries.remove(delivery);
//                }
            }
        }
    }

    private boolean canStoreMaterial(Material material) {
        return material.equals(this.material) || (this.material == null);
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void snapshotAmountTons() {

    }

    public double getBaseAmountTons() {
        return baseAmountTons;
    }

    public LocalDateTime getBaseAmountTonsDate() {
        return baseAmountTonsDate;
    }

    public List<WarehouseActivity> getWarehouseActivities() {
        return activityWindow.getActivityList();
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public LocalDateTime getEarliestDeliveryDate() {
        return deliveries.stream()
                .map(Delivery::getDeliveredDateTime)
                .min(LocalDateTime::compareTo)
                .orElse(null);  // Return null if no deliveries
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", seller=" + seller +
                ", material=" + material +
                ", activityWindow=" + activityWindow +
                ", baseAmountTons=" + baseAmountTons +
                ", baseAmountTonsDate=" + baseAmountTonsDate +
                ", deliveries=" + deliveries +
                '}';
    }

    public double getCurrentTons() {
        return baseAmountTons + (activityWindow.calculateCurrentAmount());
    }
}
