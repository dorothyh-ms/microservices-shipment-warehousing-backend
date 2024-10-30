package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.domain.WarehouseActivityType;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.in.WarehouseContentsProjector;
import be.kdg.prog6.landside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WarehouseContentsProjectorImpl implements WarehouseContentsProjector {

    private final WarehouseLoadPort warehouseLoadPort;
    private final UpdateWarehousePort updateWarehousePort;

    public WarehouseContentsProjectorImpl(WarehouseLoadPort warehouseLoadPort, UpdateWarehousePort updateWarehousePort) {
        this.warehouseLoadPort = warehouseLoadPort;
        this.updateWarehousePort = updateWarehousePort;
    }

    @Override
    public void projectWarehouseContents(UUID warehouseId, WarehouseActivityType activityType, double amountTons) {

        Warehouse warehouse = warehouseLoadPort.loadWarehouse(warehouseId);

        warehouse.changeCurrentAmountTons(amountTons, activityType);

        //TODO: save warehouse
        updateWarehousePort.updateWarehouse(warehouse);

    }
}
