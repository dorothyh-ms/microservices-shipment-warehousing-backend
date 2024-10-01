package be.kdg.prog6.landside.ports.out;

import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.domain.WeighbridgeMeasurement;

public interface WeighbridgeTransactionStartedPort {
    WeighBridgeTransaction weighbridgeTransactionStarted(WeighBridgeTransaction transaction);
}
