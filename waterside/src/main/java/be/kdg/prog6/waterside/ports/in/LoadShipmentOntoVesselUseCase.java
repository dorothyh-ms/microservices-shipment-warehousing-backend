package be.kdg.prog6.waterside.ports.in;

import be.kdg.prog6.waterside.domain.ShippingOrder;

import java.util.UUID;

public interface LoadShipmentOntoVesselUseCase {

    public ShippingOrder loadShipmentOntoVessel(UUID shippingOrderNumber);
}
