package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;

import java.util.List;
import java.util.UUID;

public interface GetActivitiesOfWarehouseUseCase {

    List<WarehouseActivity> getWarehouseActivities(UUID warehouseId);
}
