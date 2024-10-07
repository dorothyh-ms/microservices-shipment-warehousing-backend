package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static be.kdg.prog6.landside.core.TestValues.SELLER_ID;
import static be.kdg.prog6.landside.core.TestValues.WAREHOUSE;

public class WarehouseLoadPortStub implements WarehouseLoadPort {

    private List<Warehouse> warehouseList;

    public WarehouseLoadPortStub() {
    }

    @Override
    public List<Warehouse> loadWarehousesBySellerId(UUID sellerId) {
        warehouseList = new ArrayList<>();
        if (SELLER_ID.equals(sellerId)){
            warehouseList.add(WAREHOUSE);
        }
        return warehouseList;
    }

    @Override
    public Warehouse loadWarehouse(UUID warehouseId) {
        return null;
    }
}
