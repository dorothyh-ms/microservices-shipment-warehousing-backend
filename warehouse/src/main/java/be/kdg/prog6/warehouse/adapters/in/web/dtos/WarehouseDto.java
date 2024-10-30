package be.kdg.prog6.warehouse.adapters.in.web.dtos;

import be.kdg.prog6.common.domain.Material;

import java.util.List;
import java.util.UUID;

public class WarehouseDto {
    private UUID id;
    private String sellerName;
    private String material;
    private List<WarehouseActivityDto> warehouseActivities;


    public WarehouseDto(UUID id, String sellerName, String material, List<WarehouseActivityDto> warehouseActivities) {
        this.id = id;
        this.sellerName = sellerName;
        this.material = material;
        this.warehouseActivities = warehouseActivities;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<WarehouseActivityDto> getWarehouseActivities() {
        return warehouseActivities;
    }

    public void setWarehouseActivities(List<WarehouseActivityDto> warehouseActivities) {
        this.warehouseActivities = warehouseActivities;
    }
}
