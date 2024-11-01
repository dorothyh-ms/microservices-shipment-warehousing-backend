package be.kdg.prog6.waterside.ports.out;

import be.kdg.prog6.waterside.domain.ShippingOrder;

import java.util.Optional;
import java.util.UUID;

public interface ShippingOrderLoadPort {
    public Optional<ShippingOrder> loadShippingOrder(UUID shippingOrderId);

    public Optional<ShippingOrder> loadShippingOrderByPurchaseOrderNumber(String purchaseOrderNumber);
}
