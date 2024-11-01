package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.domain.ShippingOrder;
import be.kdg.prog6.waterside.domain.ShippingOrderStatus;
import be.kdg.prog6.waterside.ports.in.CompleteShippingOrderCommand;
import be.kdg.prog6.waterside.ports.in.CompleteShippingOrderUseCase;
import be.kdg.prog6.waterside.ports.out.ShippingOrderLoadPort;
import be.kdg.prog6.waterside.ports.out.ShippingOrderUpdatePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultCompleteShippingOrderUseCase implements CompleteShippingOrderUseCase {
    private final ShippingOrderLoadPort shippingOrderLoadPort;
    private final ShippingOrderUpdatePort shippingOrderUpdatePort;

    public DefaultCompleteShippingOrderUseCase(ShippingOrderLoadPort shippingOrderLoadPort, ShippingOrderUpdatePort shippingOrderUpdatePort) {
        this.shippingOrderLoadPort = shippingOrderLoadPort;
        this.shippingOrderUpdatePort = shippingOrderUpdatePort;
    }

    @Override
    public boolean completeShippingOrder(CompleteShippingOrderCommand command) {
        Optional<ShippingOrder> shippingOrderOptional = shippingOrderLoadPort.loadShippingOrderByPurchaseOrderNumber(command.purchaseOrderNumber());
        if (shippingOrderOptional.isPresent()){
            ShippingOrder so = shippingOrderOptional.get();
            so.setStatus(ShippingOrderStatus.COMPLETED);
            shippingOrderUpdatePort.updateShippingOrder(so);
        }

        return shippingOrderOptional.isPresent();
    }
}
