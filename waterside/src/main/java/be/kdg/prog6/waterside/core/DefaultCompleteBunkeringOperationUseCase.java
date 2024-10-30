package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.domain.BunkeringOperation;
import be.kdg.prog6.waterside.exceptions.BunkeringOperationNotFoundException;
import be.kdg.prog6.waterside.ports.in.CompleteBunkeringOperationUseCase;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationLoadPort;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationUpdatePort;
import be.kdg.prog6.waterside.ports.out.LoadShipmentOntoVesselPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultCompleteBunkeringOperationUseCase implements CompleteBunkeringOperationUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCompleteBunkeringOperationUseCase.class);

    private final BunkeringOperationLoadPort bunkeringOperationLoadPort;
    private final BunkeringOperationUpdatePort bunkeringOperationUpdatePort;

    public DefaultCompleteBunkeringOperationUseCase(BunkeringOperationLoadPort bunkeringOperationLoadPort, BunkeringOperationUpdatePort bunkeringOperationUpdatePort) {
        this.bunkeringOperationLoadPort = bunkeringOperationLoadPort;
        this.bunkeringOperationUpdatePort = bunkeringOperationUpdatePort;
    }

    @Override
    public BunkeringOperation completeBunkeringOperationOfShippingOrder(UUID shippingOrderId) {
        LOGGER.info("DefaultCompleteBunkeringOperationUseCase is running completeBunkeringOperationOfShippingOrder {}", shippingOrderId);
        Optional<BunkeringOperation> boOptional = bunkeringOperationLoadPort.loadBunkeringOperationByShippingOrderId(shippingOrderId);
        if (boOptional.isEmpty()){
            throw new BunkeringOperationNotFoundException(String.format("Bunkering operation of shipping order with id {} does not exist", shippingOrderId));
        }
        BunkeringOperation bo = boOptional.get();
        bo.setDateCompleted(LocalDateTime.now());
        bunkeringOperationUpdatePort.updateBunkeringOperation(bo);
        return bo;
    }
}
