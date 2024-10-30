package be.kdg.prog6.landside.adapters.in.amqp;

import be.kdg.prog6.common.events.WarehouseActivityCreatedEvent;
import be.kdg.prog6.landside.ports.in.WarehouseContentsProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WarehouseActivityCreatedListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseActivityCreatedListener.class);
    private static final String CREATED_ACTIVITIES_QUEUE = "warehouse_events_queue";

    private final WarehouseContentsProjector projector;



    public WarehouseActivityCreatedListener(WarehouseContentsProjector projector) {
        this.projector = projector;
    }


    @RabbitListener(queues=CREATED_ACTIVITIES_QUEUE)
    public void warehouseActivityCreated(final WarehouseActivityCreatedEvent event){
        LOGGER.info("{} activity was created on warehouse {} with amount {}",
                event.action(),
                event.warehouseId(),
                event.amountTons()
                );

        projector.projectWarehouseContents(event.warehouseId(), event.action(), event.amountTons());

    }

}
