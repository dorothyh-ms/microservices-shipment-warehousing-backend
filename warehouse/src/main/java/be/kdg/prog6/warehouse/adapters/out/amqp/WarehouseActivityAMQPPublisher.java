package be.kdg.prog6.warehouse.adapters.out.amqp;

import be.kdg.prog6.common.events.WarehouseActivityCreatedEvent;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;
import be.kdg.prog6.warehouse.ports.out.WarehouseActivityCreatedPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseActivityAMQPPublisher implements WarehouseActivityCreatedPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseActivityAMQPPublisher.class);
    private static final String EXCHANGE_NAME = "warehouse_events";
    private final RabbitTemplate rabbitTemplate;

    public WarehouseActivityAMQPPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void warehouseActivityCreated(UUID warehouseId, WarehouseActivity activity) {
        LOGGER.info("Publishing warehouse activity {} for warehouse {}", activity, warehouseId);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", new WarehouseActivityCreatedEvent(warehouseId, activity.action(), activity.amountTons()));
    }
}
