package be.kdg.prog6.waterside.ports.in;

import be.kdg.prog6.common.commands.CreateShipmentOrderCommand;
import be.kdg.prog6.waterside.domain.ShippingOrder;

public interface CreateShippingOrderUseCase {
    public ShippingOrder createShippingOrder(CreateShipmentOrderCommand createShipmentOrderCommand);
}
