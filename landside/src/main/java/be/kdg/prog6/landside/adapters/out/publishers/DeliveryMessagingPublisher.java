package be.kdg.prog6.landside.adapters.out.publishers;


import be.kdg.prog6.common.events.WeighbridgeDepartureEvent;
import be.kdg.prog6.common.events.WarehouseActivityType;
import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.ports.out.PayloadDeliveredPort;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMessagingPublisher implements PayloadDeliveredPort {
    private static final String EXCHANGE_NAME = "warehouse_exchange";
    private final RabbitTemplate rabbitTemplate;

    public DeliveryMessagingPublisher(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void deliveryCompleted(WeighBridgeTransaction weighBridgeTransaction) {

        final String routingKey = "weighbridge_departures";
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, new WeighbridgeDepartureEvent(
                weighBridgeTransaction.getAppointment().getMaterialUUID(),
                weighBridgeTransaction.getAppointment().getWarehouse().getId(),
                weighBridgeTransaction.getWeightOut().weighDateTime(),
                weighBridgeTransaction.getWeightIn().weightTons() - weighBridgeTransaction.getWeightOut().weightTons(),
                WarehouseActivityType.DELIVERY
        ));

    }
}
