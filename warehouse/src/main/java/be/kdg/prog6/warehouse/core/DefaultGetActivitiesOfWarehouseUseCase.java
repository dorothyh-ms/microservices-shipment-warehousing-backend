package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;
import be.kdg.prog6.warehouse.ports.in.GetActivitiesOfWarehouseUseCase;
import be.kdg.prog6.warehouse.ports.out.LoadWarehousePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultGetActivitiesOfWarehouseUseCase implements GetActivitiesOfWarehouseUseCase {

    private final LoadWarehousePort loadWarehousePort;

    public DefaultGetActivitiesOfWarehouseUseCase(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    public List<WarehouseActivity> getWarehouseActivities(UUID warehouseId) {
        Optional<Warehouse> warehouseOptional = loadWarehousePort.loadWarehouseById(warehouseId);
        if (warehouseOptional.isPresent()){
            return warehouseOptional.get().getWarehouseActivities();
        }
        return List.of();
    }
}
