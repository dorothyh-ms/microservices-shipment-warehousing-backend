package be.kdg.prog6.waterside.ports.in;

import be.kdg.prog6.waterside.domain.BunkeringOperation;

import java.util.UUID;

public interface CompleteBunkeringOperationUseCase {
    BunkeringOperation completeBunkeringOperationOfShippingOrder(UUID bunkeringOperationId);
}
