package be.kdg.prog6.waterside.ports.out;

import be.kdg.prog6.waterside.domain.ShippingOrder;

public interface ShippingOrderCreatePort {
    public void shippingOrderCreated(ShippingOrder so);
}
