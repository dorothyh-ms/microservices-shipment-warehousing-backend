package be.kdg.prog6.waterside.core;

import be.kdg.prog6.common.commands.CreateShipmentOrderCommand;
import be.kdg.prog6.waterside.domain.BunkeringOperation;
import be.kdg.prog6.waterside.domain.InspectionOperation;
import be.kdg.prog6.waterside.domain.ShippingOrder;
import be.kdg.prog6.waterside.domain.ShippingOrderStatus;
import be.kdg.prog6.waterside.ports.in.CreateShippingOrderUseCase;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationCreatePort;
import be.kdg.prog6.waterside.ports.out.InspectionOperationCreatePort;
import be.kdg.prog6.waterside.ports.out.ShippingOrderCreatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class DefaultCreateShippingOrderUseCase implements CreateShippingOrderUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCreateShippingOrderUseCase.class);

    private List<ShippingOrderCreatePort> shippingOrderCreatedPortList;
    private BunkeringOperationCreatePort bunkeringOperationCreatePort;
    private InspectionOperationCreatePort inspectionOperationCreatePort;

    public DefaultCreateShippingOrderUseCase(List<ShippingOrderCreatePort> shippingOrderCreatedPortList, BunkeringOperationCreatePort bunkeringOperationCreatePort, InspectionOperationCreatePort inspectionOperationCreatePort) {
        this.shippingOrderCreatedPortList = shippingOrderCreatedPortList;
        this.bunkeringOperationCreatePort = bunkeringOperationCreatePort;
        this.inspectionOperationCreatePort = inspectionOperationCreatePort;
    }

    @Override
    public ShippingOrder createShippingOrder(CreateShipmentOrderCommand createShipmentOrderCommand) {
        LOGGER.info("DefaultCreateShippingOrderUseCase is running createShippingOrder with command {}", createShipmentOrderCommand);
        ShippingOrder so = new ShippingOrder(
                UUID.randomUUID(),
                createShipmentOrderCommand.shippingOrderNo(),
                createShipmentOrderCommand.purchaseOrderNo(),
                createShipmentOrderCommand.vesselNumber(),
                ShippingOrderStatus.OUTSTANDING
                );
        BunkeringOperation bo = new BunkeringOperation(UUID.randomUUID(), so);
        InspectionOperation io = new InspectionOperation(UUID.randomUUID(), so);
        shippingOrderCreatedPortList.forEach(port -> port.shippingOrderCreated(so));
        bunkeringOperationCreatePort.bunkeringOperationCreated(bo);
        inspectionOperationCreatePort.inspectionOperationCreatedPort(io);
        return so;

    }
}
