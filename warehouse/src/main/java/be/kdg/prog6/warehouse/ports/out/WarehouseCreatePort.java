package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.Warehouse;

public interface WarehouseCreatePort {
    void warehouseCreated(Warehouse warehouse);
}
