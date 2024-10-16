package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.ports.in.GetWarehousesUseCase;
import be.kdg.prog6.warehouse.ports.out.LoadWarehousePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetWarehousesUseCase implements GetWarehousesUseCase {

    private final LoadWarehousePort loadWarehousePort;

    public DefaultGetWarehousesUseCase(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    public List<Warehouse> getWarehouses() {
        return loadWarehousePort.loadWarehouses();
    }
}
