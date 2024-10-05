package be.kdg.prog6.warehouse.ports.out;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;

import java.util.UUID;

public interface WarehouseActivityCreatedPort {

    void warehouseActivityCreated(UUID warehouseId, WarehouseActivity activity);
}
