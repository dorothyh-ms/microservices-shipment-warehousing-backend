package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.common.commands.RemoveMaterialForShipmentCommand;
import be.kdg.prog6.common.domain.PurchaseOrderLine;
import be.kdg.prog6.common.domain.WarehouseActivityType;
import be.kdg.prog6.warehouse.adapters.in.amqp.WeighbridgeDepartureListener;
import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.domain.PurchaseOrderStatus;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;
import be.kdg.prog6.warehouse.ports.in.RemoveMaterialFromWarehouseUseCase;
import be.kdg.prog6.warehouse.ports.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultRemoveMaterialFromWarehouseUseCase implements RemoveMaterialFromWarehouseUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeighbridgeDepartureListener.class);
    private final LoadPurchaseOrderPort purchaseOrderLoadPort;
    private final LoadWarehousePort loadWarehousePort;
    private final UpdateWarehousePort updateWarehousePort;
    private final List<WarehouseActivityCreatedPort> warehouseActivityCreatedPortList;
    private final List<PurchaseOrderFulfilledPort> purchaseOrderFulfilledPortList;

    public DefaultRemoveMaterialFromWarehouseUseCase(LoadPurchaseOrderPort purchaseOrderLoadPort, LoadWarehousePort loadWarehousePort, UpdateWarehousePort updateWarehousePort, List<WarehouseActivityCreatedPort> warehouseActivityCreatedPortList, List<PurchaseOrderFulfilledPort> purchaseOrderFulfilledPortList) {
        this.purchaseOrderLoadPort = purchaseOrderLoadPort;
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehousePort = updateWarehousePort;
        this.warehouseActivityCreatedPortList = warehouseActivityCreatedPortList;
        this.purchaseOrderFulfilledPortList = purchaseOrderFulfilledPortList;
    }

    @Override
    public void removeMaterialFromWarehouses(RemoveMaterialForShipmentCommand command) {
        LOGGER.info("DefaultRemoveMaterialFromWarehouseUseCase is running removeMaterialFromWarehouses with command {}", command);
        Optional<PurchaseOrder> poOptional = purchaseOrderLoadPort.loadPurchaseOrderByPurchaseOrderNumber(command.purchaseOrderNumber());
        PurchaseOrder po = poOptional.get();
        List<PurchaseOrderLine> poLs = po.getOrderLines();

        poLs.forEach(pol -> {
            Optional<Warehouse> warehouseOptional =loadWarehousePort.loadWarehouseBySellerIdAndMaterial(po.getSeller().getId(), pol.material());
            Warehouse warehouse =warehouseOptional.get();
            WarehouseActivity activity = warehouse.addActivity(new WarehouseActivity(
                    WarehouseActivityType.SHIPMENT,
                    pol.material(),
                    pol.amountTons(),
                    LocalDateTime.now()
                    ));
            updateWarehousePort.updateWarehouse(warehouse);

            warehouseActivityCreatedPortList.forEach(port -> port.warehouseActivityCreated(warehouse.getId(), activity));
        });
        po.setStatus(PurchaseOrderStatus.FULFILLED);
        purchaseOrderFulfilledPortList.forEach(port -> port.purchaseOrderFulfilled(po));

    }
}
