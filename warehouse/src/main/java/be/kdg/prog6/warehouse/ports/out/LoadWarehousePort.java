package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.Warehouse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadWarehousePort {

    Optional<Warehouse> loadWarehouseById(UUID warehouseId);

    List<Warehouse> loadWarehouses();
}
