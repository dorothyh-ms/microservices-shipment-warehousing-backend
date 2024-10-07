package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;
import be.kdg.prog6.warehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.warehouse.exceptions.IncorrectMaterialException;
import be.kdg.prog6.warehouse.exceptions.SellerNotFoundException;
import be.kdg.prog6.warehouse.ports.in.AddMaterialToWarehouseCommand;
import be.kdg.prog6.warehouse.ports.in.AddMaterialToWarehouseUseCase;
import be.kdg.prog6.warehouse.ports.out.*;
import org.springframework.stereotype.Service;
import main.java.be.kdg.prog6.warehouse.domain.Seller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import main.java.be.kdg.prog6.warehouse.domain.Material;


@Service
public class DefaultAddMaterialToWarehouseUseCase implements AddMaterialToWarehouseUseCase {

    private final LoadWarehousePort loadWarehousePort;
    private final UpdateWarehousePort updateWarehousePort;
    private final WarehouseCreatePort warehouseCreatePort;
    private final LoadMaterialPort loadMaterialPort;
    private final List<WarehouseActivityCreatedPort> warehouseActivityCreatedPortList;
    private final LoadSellerPort loadSellerPort;

    public DefaultAddMaterialToWarehouseUseCase(LoadWarehousePort loadWarehousePort, UpdateWarehousePort updateWarehousePort, WarehouseCreatePort warehouseCreatePort, LoadMaterialPort loadMaterialPort, LoadSellerPort loadSellerPort, List<WarehouseActivityCreatedPort> warehouseActivityCreatedPortList) {
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehousePort = updateWarehousePort;
        this.warehouseCreatePort = warehouseCreatePort;
        this.loadMaterialPort = loadMaterialPort;
        this.loadSellerPort = loadSellerPort;
        this.warehouseActivityCreatedPortList = warehouseActivityCreatedPortList;
    }

    @Override
    public void addMaterialToWarehouse(AddMaterialToWarehouseCommand addMaterialToWarehouseCommand) {
        final Optional<Warehouse> warehouseOptional = loadWarehousePort.loadWarehouseById(addMaterialToWarehouseCommand.warehouseId());
        final Optional<Material> materialOptional  = loadMaterialPort.loadMaterialById(addMaterialToWarehouseCommand.materialID());

        if (materialOptional.isEmpty()){
            throw new IncorrectMaterialException();
        }

        Warehouse warehouse;
        if (warehouseOptional.isEmpty()){
            final Optional<Seller> sellerOptional = loadSellerPort.loadSellerById(addMaterialToWarehouseCommand.sellerId());
            if (sellerOptional.isEmpty()) {
                throw new SellerNotFoundException();
            }
            warehouse = new Warehouse(
                    addMaterialToWarehouseCommand.warehouseId(),
                    sellerOptional.get(),
                    materialOptional.get(),
                    new WarehouseActivityWindow()
            );
            warehouseCreatePort.warehouseCreated(warehouse);
        } else {
            warehouse = warehouseOptional.get();
        }
        updateWarehousePort.updateWarehouse(warehouse);
        WarehouseActivity activity = warehouse.addMaterial(
                materialOptional.get(),
                addMaterialToWarehouseCommand.amountTons(),
                LocalDateTime.now()
                );
        warehouseActivityCreatedPortList.forEach(port -> port.warehouseActivityCreated(warehouse.getId(), activity));

    }
}
