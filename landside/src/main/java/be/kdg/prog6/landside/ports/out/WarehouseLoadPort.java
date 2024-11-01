package be.kdg.prog6.landside.ports.out;

import be.kdg.prog6.landside.domain.Warehouse;

import java.util.List;
import java.util.UUID;

public interface WarehouseLoadPort {
    List<Warehouse> loadWarehousesBySellerName(String sellerName);

    Warehouse loadWarehouse(UUID warehouseId);

    List<Warehouse> loadWarehouses(String sellerName);
}
