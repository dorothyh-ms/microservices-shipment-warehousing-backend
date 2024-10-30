package be.kdg.prog6.waterside.ports.in;

import be.kdg.prog6.waterside.domain.InspectionOperation;

import java.util.List;

public interface GetInspectionOperationsUseCase {

    public List<InspectionOperation> getInspectionOperations(boolean outstandingOnly);
}
