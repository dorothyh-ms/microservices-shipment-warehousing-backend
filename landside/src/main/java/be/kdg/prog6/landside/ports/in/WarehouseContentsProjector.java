package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.common.domain.WarehouseActivityType;

import java.util.UUID;

public interface WarehouseContentsProjector {
    void projectWarehouseContents(UUID warehouseId, WarehouseActivityType activityType, double amountTons);
}
