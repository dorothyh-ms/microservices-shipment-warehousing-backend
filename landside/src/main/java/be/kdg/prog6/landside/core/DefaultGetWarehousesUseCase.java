package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.in.GetWarehousesUseCase;
import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetWarehousesUseCase implements GetWarehousesUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGetWarehousesUseCase.class);
    private final WarehouseLoadPort warehouseLoadPort;

    public DefaultGetWarehousesUseCase(WarehouseLoadPort warehouseLoadPort) {
        this.warehouseLoadPort = warehouseLoadPort;
    }

    @Override
    public List<Warehouse> getWarehouses(String sellerName) {
        LOGGER.info("DefaultGetWarehousesUseCase is running getWarehouses");
        return warehouseLoadPort.loadWarehouses(sellerName);
    }
}
