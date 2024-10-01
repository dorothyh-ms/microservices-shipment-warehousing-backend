package be.kdg.prog6.landside.ports.out;

import be.kdg.prog6.landside.domain.WeighBridgeTransaction;

import java.util.Optional;

public interface WeighbridgeTransactionLoadPort {

    public Optional<WeighBridgeTransaction> loadWeighBridgeTransactionByLicensePlate(String licensePlate);
}
