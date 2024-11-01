package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static be.kdg.prog6.landside.core.TestValues.SELLER_NAME;
import static be.kdg.prog6.landside.core.TestValues.WAREHOUSE;

public class WarehouseLoadPortStub implements WarehouseLoadPort {

    private List<Warehouse> warehouseList;

    public WarehouseLoadPortStub() {
    }

    @Override
    public List<Warehouse> loadWarehousesBySellerName(String sellerName) {
        warehouseList = new ArrayList<>();
        if (SELLER_NAME.equals(sellerName)){
            warehouseList.add(WAREHOUSE);
        }
        return warehouseList;
    }

    @Override
    public List<Warehouse> loadWarehouses(String sellerName) {
        return List.of();
    }

    @Override
    public Warehouse loadWarehouse(UUID warehouseId) {
        return null;
    }
}
