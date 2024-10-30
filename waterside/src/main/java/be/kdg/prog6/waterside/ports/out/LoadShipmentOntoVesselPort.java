package be.kdg.prog6.waterside.ports.out;

import be.kdg.prog6.waterside.domain.ShippingOrder;

public interface LoadShipmentOntoVesselPort {
    public void loadShipmentOntoVessel(ShippingOrder so);
}
