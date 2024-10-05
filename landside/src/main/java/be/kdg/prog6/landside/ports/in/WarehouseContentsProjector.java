package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.common.events.WarehouseActivityType;
import be.kdg.prog6.landside.domain.Warehouse;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseContentsProjector {
    void projectWarehouseContents(UUID warehouseId, WarehouseActivityType activityType, double amountTons);
}
