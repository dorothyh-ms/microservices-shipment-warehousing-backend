package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.ports.in.commands.FinalizeWeighBridgeTransactionCommand;

public interface WeighbridgeDepartureUseCase {
    public WeighBridgeTransaction recordTruckDepartureWeight(FinalizeWeighBridgeTransactionCommand finalizeWeighBridgeTransactionCommand);
}
