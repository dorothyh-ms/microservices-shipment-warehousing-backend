package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.domain.BunkeringOperation;
import be.kdg.prog6.waterside.ports.in.GetBunkeringOperationsUseCase;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetBunkeringOperationsUseCase implements GetBunkeringOperationsUseCase {
    private final BunkeringOperationLoadPort bunkeringOperationLoadPort;

    public DefaultGetBunkeringOperationsUseCase(BunkeringOperationLoadPort bunkeringOperationLoadPort) {
        this.bunkeringOperationLoadPort = bunkeringOperationLoadPort;
    }

    @Override
    public List<BunkeringOperation> getBunkeringOperations(boolean outstandingOnly) {
        return bunkeringOperationLoadPort.loadBunkeringOperations(outstandingOnly);
    }
}
