package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.ports.in.CompleteShippingOrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class DefaultCompleteShippingOrderUseCase implements CompleteShippingOrderUseCase {
    @Override
    public void completeShippingOrder(String purchaseOrderNumber) {

    }
}
