package be.kdg.prog6.landside.ports.out;

import be.kdg.prog6.landside.domain.WeighBridgeTransaction;

public interface PayloadDeliveredPort {

    void deliveryCompleted(WeighBridgeTransaction weighBridgeTransaction);
}
