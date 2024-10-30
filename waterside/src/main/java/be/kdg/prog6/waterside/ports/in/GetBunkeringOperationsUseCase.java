package be.kdg.prog6.waterside.ports.in;

import be.kdg.prog6.waterside.domain.BunkeringOperation;

import java.util.List;

public interface GetBunkeringOperationsUseCase {
    public List<BunkeringOperation> getBunkeringOperations(boolean outstandingOnly);
}
