package be.kdg.prog6.waterside.ports.out;

import be.kdg.prog6.waterside.domain.InspectionOperation;

public interface InspectionOperationCreatePort {
    public void inspectionOperationCreatedPort(InspectionOperation io);
}
