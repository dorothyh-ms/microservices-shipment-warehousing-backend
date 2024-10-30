package be.kdg.prog6.waterside.adapters.out.amqp;

import be.kdg.prog6.common.commands.RemoveMaterialForShipmentCommand;
import be.kdg.prog6.waterside.domain.ShippingOrder;
import be.kdg.prog6.waterside.ports.out.LoadShipmentOntoVesselPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoadVesselForShipmentAMQPPublisher implements LoadShipmentOntoVesselPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadVesselForShipmentAMQPPublisher.class);
    private static final String EXCHANGE_NAME = "shipping_order_commands";
    private final RabbitTemplate rabbitTemplate;

    public LoadVesselForShipmentAMQPPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void loadShipmentOntoVessel(ShippingOrder so) {
        LOGGER.info("LoadVesselForShipmentAMQPPublisher is running shippingOrderCreated with shipping order {}", so);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "shipping_order_commands", new RemoveMaterialForShipmentCommand(so.getPurchaseOrderNo()));
    }

}
