package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.common.domain.WarehouseActivityType;
import be.kdg.prog6.warehouse.adapters.in.amqp.PurchaseOrderListener;
import be.kdg.prog6.warehouse.domain.Delivery;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;
import be.kdg.prog6.warehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.warehouse.exceptions.IncorrectMaterialException;
import be.kdg.prog6.warehouse.exceptions.SellerNotFoundException;
import be.kdg.prog6.warehouse.ports.in.AddMaterialToWarehouseCommand;
import be.kdg.prog6.warehouse.ports.in.AddMaterialToWarehouseUseCase;
import be.kdg.prog6.warehouse.ports.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import main.java.be.kdg.prog6.warehouse.domain.Seller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@Service
public class DefaultAddMaterialToWarehouseUseCase implements AddMaterialToWarehouseUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderListener.class);
    private final LoadWarehousePort loadWarehousePort;
    private final UpdateWarehousePort updateWarehousePort;
    private final WarehouseCreatePort warehouseCreatePort;

    private final List<WarehouseActivityCreatedPort> warehouseActivityCreatedPortList;
    private final LoadSellerPort loadSellerPort;

    public DefaultAddMaterialToWarehouseUseCase(LoadWarehousePort loadWarehousePort, UpdateWarehousePort updateWarehousePort, WarehouseCreatePort warehouseCreatePort, LoadSellerPort loadSellerPort, List<WarehouseActivityCreatedPort> warehouseActivityCreatedPortList) {
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehousePort = updateWarehousePort;
        this.warehouseCreatePort = warehouseCreatePort;
        this.loadSellerPort = loadSellerPort;
        this.warehouseActivityCreatedPortList = warehouseActivityCreatedPortList;
    }

    @Override
    public void addMaterialToWarehouse(AddMaterialToWarehouseCommand addMaterialToWarehouseCommand) {
        LOGGER.info("DefaultAddMaterialToWarehouseUseCase is running addMaterialToWarehouse with command {}", addMaterialToWarehouseCommand);
        final Optional<Warehouse> warehouseOptional = loadWarehousePort.loadWarehouseById(addMaterialToWarehouseCommand.warehouseId());

        Warehouse warehouse;
        if (warehouseOptional.isEmpty()){
            LOGGER.info("Creating warehouse with id {}", addMaterialToWarehouseCommand.warehouseId());
            final Optional<Seller> sellerOptional = loadSellerPort.loadSellerById(addMaterialToWarehouseCommand.sellerId());
            if (sellerOptional.isEmpty()) {
                throw new SellerNotFoundException();
            }
            warehouse = new Warehouse(
                    addMaterialToWarehouseCommand.warehouseId(),
                    sellerOptional.get(),
                    addMaterialToWarehouseCommand.material(),
                    new WarehouseActivityWindow(),
                    new ArrayList<Delivery>()
            );
            warehouseCreatePort.warehouseCreated(warehouse);
            LOGGER.info("Warehouse created {}", warehouse);
        } else {
            warehouse = warehouseOptional.get();
            LOGGER.info("Warehouse found {}", warehouse);
        }

        WarehouseActivity activity = warehouse.addActivity(
                new WarehouseActivity(WarehouseActivityType.DELIVERY,
                        addMaterialToWarehouseCommand.material(),
                        addMaterialToWarehouseCommand.amountTons(),
                        LocalDateTime.now()));
        LOGGER.info("Warehouse {} updated", warehouse);
        updateWarehousePort.updateWarehouse(warehouse);

        warehouseActivityCreatedPortList.forEach(port -> port.warehouseActivityCreated(warehouse.getId(), activity));
    }
}
