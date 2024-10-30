package be.kdg.prog6.waterside.ports.out;

import be.kdg.prog6.waterside.domain.BunkeringOperation;
import be.kdg.prog6.waterside.domain.InspectionOperation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BunkeringOperationLoadPort {
    List<BunkeringOperation> loadBunkeringOperations(boolean outstandingOnly);


    public Optional<BunkeringOperation> loadBunkeringOperationByShippingOrderId(UUID id);
}
