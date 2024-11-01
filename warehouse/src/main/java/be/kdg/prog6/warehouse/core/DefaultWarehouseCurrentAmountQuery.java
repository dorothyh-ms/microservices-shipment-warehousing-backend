package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.ports.in.WarehouseCurrentAmountCommand;
import be.kdg.prog6.warehouse.ports.in.WarehouseCurrentAmountQuery;
import be.kdg.prog6.warehouse.ports.out.LoadWarehousePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultWarehouseCurrentAmountQuery implements WarehouseCurrentAmountQuery {

    private final LoadWarehousePort loadWarehousePort;

    public DefaultWarehouseCurrentAmountQuery(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    public double getCurrentAmountQuery(WarehouseCurrentAmountCommand command) {
        Optional<Warehouse> warehouseOptional = loadWarehousePort.loadWarehouseById(command.warehouseId());
        return warehouseOptional.map(Warehouse::getCurrentTons).orElse(0.0);
    }
}
