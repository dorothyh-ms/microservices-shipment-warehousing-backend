package be.kdg.prog6.warehouse.adapters.in.amqp;

import be.kdg.prog6.common.events.WeighbridgeDepartureEvent;
import be.kdg.prog6.warehouse.ports.in.AddMaterialToWarehouseCommand;
import be.kdg.prog6.warehouse.ports.in.AddMaterialToWarehouseUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WeighbridgeDepartureListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeighbridgeDepartureListener.class);
    private static final String WEIGHBRIDGE_DEPARTURES_QUEUE = "weighbridge_departures";

    private final AddMaterialToWarehouseUseCase addMaterialToWarehouseUseCase;

    public WeighbridgeDepartureListener(AddMaterialToWarehouseUseCase addMaterialToWarehouseUseCase) {
        this.addMaterialToWarehouseUseCase = addMaterialToWarehouseUseCase;
    }

    @RabbitListener(queues = WEIGHBRIDGE_DEPARTURES_QUEUE)
    public void truckDelivered(final WeighbridgeDepartureEvent event) {
        LOGGER.info("WeighbridgeDepartureListener is running truckDelivered with {}", event);
        addMaterialToWarehouseUseCase.addMaterialToWarehouse(
                new AddMaterialToWarehouseCommand(event.material(), event.sellerId(), event.warehouseId(), event.deliveryTime(), event.amount())
        );

    }


}
