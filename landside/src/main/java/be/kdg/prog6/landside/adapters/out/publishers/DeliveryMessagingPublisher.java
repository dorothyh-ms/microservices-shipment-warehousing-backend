package be.kdg.prog6.landside.adapters.out.publishers;


import be.kdg.prog6.common.events.WeighbridgeDepartureEvent;
import be.kdg.prog6.common.events.WarehouseActivityType;
import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.ports.out.PayloadDeliveredPort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMessagingPublisher implements PayloadDeliveredPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryMessagingPublisher.class);
    private static final String EXCHANGE_NAME = "landside_deliveries";
    private final RabbitTemplate rabbitTemplate;

    public DeliveryMessagingPublisher(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void deliveryCompleted(WeighBridgeTransaction weighBridgeTransaction) {

        final String routingKey = "weighbridge_departures";
        LOGGER.info("Publishing weighbridge transaction {}", weighBridgeTransaction);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, new WeighbridgeDepartureEvent(
                weighBridgeTransaction.getAppointment().getMaterialUUID(),
                weighBridgeTransaction.getAppointment().getWarehouse().getSellerId(),
                weighBridgeTransaction.getAppointment().getWarehouse().getId(),
                weighBridgeTransaction.getWeightOut().weighDateTime(),
                weighBridgeTransaction.getWeightIn().weightTons() - weighBridgeTransaction.getWeightOut().weightTons()
        ));

    }
}
