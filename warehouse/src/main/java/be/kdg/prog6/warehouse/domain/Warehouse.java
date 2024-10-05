package be.kdg.prog6.warehouse.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import be.kdg.prog6.common.events.WarehouseActivityType;
import be.kdg.prog6.warehouse.exceptions.IncorrectMaterialException;
import main.java.be.kdg.prog6.warehouse.domain.Material;
import main.java.be.kdg.prog6.warehouse.domain.Seller;

public class Warehouse {

    private UUID id;
    private Seller seller;
    private Material material;

    private WarehouseActivityWindow activityWindow;

    private double baseAmountTons;

    private LocalDateTime baseAmountTonsDate;


    public Warehouse(Seller seller, Material material, WarehouseActivityWindow activityWindow) {
        this.id = UUID.randomUUID();
        this.seller = seller;
        this.material = material;
        this.activityWindow = activityWindow;
    }

    public Warehouse(UUID id, Seller seller, Material material, WarehouseActivityWindow activityWindow) {
        this.id = id;
        this.seller = seller;
        this.material = material;
        this.activityWindow = activityWindow;
    }



    public WarehouseActivity addMaterial(Material material, double amountTons, LocalDateTime datetime){
        if (canStoreMaterial(material)){
        WarehouseActivity warehouseActivity = new WarehouseActivity(WarehouseActivityType.DELIVERY, amountTons, datetime);
        this.setMaterial(material);
        return warehouseActivity;
        } else {
            throw new IncorrectMaterialException();
        }
    }

    private boolean canStoreMaterial(Material material){
        return material.getId().equals(this.material.getId()) || (this.material == null);
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

    public void snapshotAmountTons(){

    }

    public double getBaseAmountTons() {
        return baseAmountTons;
    }

    public LocalDateTime getBaseAmountTonsDate() {
        return baseAmountTonsDate;
    }
}
