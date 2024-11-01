package be.kdg.prog6.waterside.adapters.in.amqp;

import be.kdg.prog6.common.events.PurchaseOrderFulfilledEvent;
import be.kdg.prog6.waterside.ports.in.CompleteShippingOrderCommand;
import be.kdg.prog6.waterside.ports.in.CompleteShippingOrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderFulfilledListener {
    private final CompleteShippingOrderUseCase completeShippingOrderUseCase;
    private static final String PURCHASE_ORDER_QUEUE = "fulfilled_purchase_orders_queue";

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderFulfilledListener.class);

    public PurchaseOrderFulfilledListener(CompleteShippingOrderUseCase completeShippingOrderUseCase) {
        this.completeShippingOrderUseCase = completeShippingOrderUseCase;
    }

    @RabbitListener(queues = PURCHASE_ORDER_QUEUE)
    public void completeShippingOrderUseCase(PurchaseOrderFulfilledEvent event){
        LOGGER.info("PurchaseOrderListener is running createPurchaseOrder with command {}", event);
        completeShippingOrderUseCase.completeShippingOrder(new CompleteShippingOrderCommand(event.purchaseOrderNumber()));
    }
}
