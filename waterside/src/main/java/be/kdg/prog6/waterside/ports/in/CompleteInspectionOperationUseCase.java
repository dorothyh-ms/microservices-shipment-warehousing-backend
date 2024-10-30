package be.kdg.prog6.waterside.ports.in;

import be.kdg.prog6.waterside.domain.InspectionOperation;

import java.util.UUID;

public interface CompleteInspectionOperationUseCase {

    public InspectionOperation completeInspectionOperation(UUID shippingOrderId, String signature);
}

