package be.kdg.prog6.warehouse.adapters.in.amqp;

import be.kdg.prog6.common.commands.RemoveMaterialForShipmentCommand;
import be.kdg.prog6.warehouse.ports.in.RemoveMaterialFromWarehouseUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RemoveMaterialForShipmentListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveMaterialForShipmentListener.class);
    private static final String SHIPMENT_ORDERS_QUEUE = "shipping_order_queue";

    private final RemoveMaterialFromWarehouseUseCase removeMaterialFromWarehouseUseCase;

    public RemoveMaterialForShipmentListener(RemoveMaterialFromWarehouseUseCase removeMaterialFromWarehouseUseCase) {
        this.removeMaterialFromWarehouseUseCase = removeMaterialFromWarehouseUseCase;
    }

    @RabbitListener(queues = SHIPMENT_ORDERS_QUEUE)
    public void materialRequestedForShipment(final RemoveMaterialForShipmentCommand command) {
        LOGGER.info("RemoveMaterialForShipmentListener is running materialRequestedForShipment with {}", command);
        removeMaterialFromWarehouseUseCase.removeMaterialFromWarehouses(command);
    }
}
