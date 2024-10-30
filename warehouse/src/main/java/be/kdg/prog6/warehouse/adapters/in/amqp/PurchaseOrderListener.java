package be.kdg.prog6.warehouse.adapters.in.amqp;

import be.kdg.prog6.warehouse.ports.in.CreatePurchaseOrderCommand;
import be.kdg.prog6.warehouse.ports.in.CreatePurchaseOrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderListener {

    private final CreatePurchaseOrderUseCase createPurchaseOrderUseCase;
    private static final String PURCHASE_ORDER_QUEUE = "created_purchase_orders_queue";

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderListener.class);

    public PurchaseOrderListener(CreatePurchaseOrderUseCase createPurchaseOrderUseCase) {
        this.createPurchaseOrderUseCase = createPurchaseOrderUseCase;
    }


    @RabbitListener(queues = PURCHASE_ORDER_QUEUE)
    public void createPurchaseOrder(CreatePurchaseOrderCommand command){
        LOGGER.info("PurchaseOrderListener is running createPurchaseOrder with command {}", command);
        createPurchaseOrderUseCase.createPurchaseOrder(command);
    }
}
