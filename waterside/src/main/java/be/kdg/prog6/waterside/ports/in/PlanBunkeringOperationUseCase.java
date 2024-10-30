package be.kdg.prog6.waterside.ports.in;

import be.kdg.prog6.waterside.domain.BunkeringOperation;

import java.time.LocalDate;
import java.util.UUID;

public interface PlanBunkeringOperationUseCase {

    public BunkeringOperation planBunkeringOperation(UUID bunkeringOperationId, LocalDate plannedDate);
}
