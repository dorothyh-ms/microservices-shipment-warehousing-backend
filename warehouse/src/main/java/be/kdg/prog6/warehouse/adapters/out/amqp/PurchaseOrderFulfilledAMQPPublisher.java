package be.kdg.prog6.warehouse.adapters.out.amqp;

import be.kdg.prog6.common.events.WarehouseActivityCreatedEvent;
import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;
import be.kdg.prog6.warehouse.ports.out.PurchaseOrderFulfilledPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PurchaseOrderFulfilledAMQPPublisher implements PurchaseOrderFulfilledPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderFulfilledAMQPPublisher.class);
    private static final String EXCHANGE_NAME = "warehouse_events";
    private final RabbitTemplate rabbitTemplate;

    public PurchaseOrderFulfilledAMQPPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void purchaseOrderFulfilled(PurchaseOrder po) {
        LOGGER.info("PurchaseOrderFulfilledAMQPPublisher is running purchaseOrderFulfilled for purchase order {}", po);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", new WarehouseActivityCreatedEvent(warehouseId, activity.action(), activity.amountTons()));
    }

    @Override
    public void purchaseOrderFulfilled(UUID warehouseId, WarehouseActivity activity) {
        LOGGER.info("Publishing warehouse activity {} for warehouse {}", activity, warehouseId);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", new WarehouseActivityCreatedEvent(warehouseId, activity.action(), activity.amountTons()));
    }
}
