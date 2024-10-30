package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;
import be.kdg.prog6.warehouse.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.warehouse.ports.in.GetWarehouseUseCase;
import be.kdg.prog6.warehouse.ports.out.LoadWarehousePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultGetWarehouseUseCase implements GetWarehouseUseCase {

    private final LoadWarehousePort loadWarehousePort;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGetWarehouseUseCase.class);

    public DefaultGetWarehouseUseCase(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    public Warehouse getWarehouse(UUID warehouseId) {
        LOGGER.info("DefaultGetWarehouseUseCase is running getWarehouseActivities with id {}", warehouseId);
        Optional<Warehouse> warehouseOptional = loadWarehousePort.loadWarehouseById(warehouseId);
        if (warehouseOptional.isEmpty()) {
            LOGGER.info("DefaultGetWarehouseUseCase could not find warehouse with id {}", warehouseId);
            throw new WarehouseNotFoundException("Warehouse with id %s does not exist".format(warehouseId.toString()));
        }
        Warehouse warehouse = warehouseOptional.get();
        LOGGER.info("DefaultGetWarehouseUseCase is returning warehouse {}", warehouse);
        return warehouse;

    }
}
