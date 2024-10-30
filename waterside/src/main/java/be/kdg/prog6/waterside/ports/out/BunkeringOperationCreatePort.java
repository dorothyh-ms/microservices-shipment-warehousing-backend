package be.kdg.prog6.waterside.ports.out;

import be.kdg.prog6.waterside.domain.BunkeringOperation;

public interface BunkeringOperationCreatePort {
    public void bunkeringOperationCreated(BunkeringOperation bo);
}
