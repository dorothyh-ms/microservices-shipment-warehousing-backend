package be.kdg.prog6.waterside.ports.out;

import be.kdg.prog6.waterside.domain.InspectionOperation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InspectionOperationLoadPort {
    List<InspectionOperation> loadInspectionOperations(boolean outstandingOnly);

    Optional<InspectionOperation> loadInspectionOperation(UUID shippingOrderId);
}
