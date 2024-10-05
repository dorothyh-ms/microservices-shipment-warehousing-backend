package be.kdg.prog6.landside.ports.out;

import be.kdg.prog6.landside.domain.Warehouse;

public interface WarehouseProjectionPort {
    void saveWarehouse(Warehouse warehouse);
}
