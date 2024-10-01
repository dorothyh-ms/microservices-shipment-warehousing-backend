package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.landside.ports.in.commands.CreateWeighBridgeTransactionCommand;
import be.kdg.prog6.landside.ports.in.commands.DeliverPayloadCommand;

public interface WeighbridgeArrivalUseCase {

    void recordTruckArrivalWeight(CreateWeighBridgeTransactionCommand createWeighBridgeTransactionCommand);
}
