package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.warehouse.domain.Warehouse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadWarehousePort {

    Optional<Warehouse> loadWarehouseById(UUID warehouseId);
    Optional<Warehouse> loadWarehouseBySellerIdAndMaterial(UUID sellerId, Material material);
}
