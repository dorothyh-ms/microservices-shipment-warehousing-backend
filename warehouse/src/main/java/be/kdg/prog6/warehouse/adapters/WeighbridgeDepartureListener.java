package be.kdg.prog6.warehouse.adapters;

import be.kdg.prog6.common.events.WeighbridgeDepartureEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WeighbridgeDepartureListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeighbridgeDepartureListener.class);
    private static final String WEIGHBRIDGE_DEPARTURES_QUEUE = "weighbridge_departures";

    @RabbitListener(queues = WEIGHBRIDGE_DEPARTURES_QUEUE)
    public void truckDelivered(final WeighbridgeDepartureEvent event) {
        LOGGER.info("Payload in {}", event);
    }


}
