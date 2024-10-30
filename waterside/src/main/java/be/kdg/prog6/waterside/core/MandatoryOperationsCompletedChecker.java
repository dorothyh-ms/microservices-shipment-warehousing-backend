package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.domain.BunkeringOperation;
import be.kdg.prog6.waterside.domain.InspectionOperation;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationLoadPort;
import be.kdg.prog6.waterside.ports.out.InspectionOperationLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MandatoryOperationsCompletedChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLoadShipmentOntoVesselUseCase.class);
    private final BunkeringOperationLoadPort bunkeringOperationLoadPort;
    private final InspectionOperationLoadPort inspectionOperationLoadPort;

    public MandatoryOperationsCompletedChecker(BunkeringOperationLoadPort bunkeringOperationLoadPort, InspectionOperationLoadPort inspectionOperationLoadPort) {
        this.bunkeringOperationLoadPort = bunkeringOperationLoadPort;
        this.inspectionOperationLoadPort = inspectionOperationLoadPort;
    }

    public boolean checkMandatoryOperationsCompleted(UUID shippingOrderId) {
        LOGGER.info("MandatoryOperationsCompletedChecker is running checkMandatoryOperationsCompleted with for shipping order with id {}", shippingOrderId);
        Optional<BunkeringOperation> boOptional = bunkeringOperationLoadPort.loadBunkeringOperationByShippingOrderId(shippingOrderId);
        Optional<InspectionOperation> ioOptional = inspectionOperationLoadPort.loadInspectionOperation(shippingOrderId);
        boolean bunkeringOperationCompleted;
        boolean inspectionOperationCompleted;
        if (boOptional.isPresent()) {
            BunkeringOperation bo = boOptional.get();
            bunkeringOperationCompleted = bo.getDateCompleted() != null;
            LOGGER.info("Bunkering operation of shipping order {} completed", bunkeringOperationCompleted);
        } else {
            LOGGER.info("Bunkering operation of shipping order {} does not exist", shippingOrderId);
            return false;
        }
        if (ioOptional.isPresent()){
            InspectionOperation io = ioOptional.get();
            inspectionOperationCompleted = (io.getSignature() != null) && (io.getDateInspected() != null);
            LOGGER.info("Inspection operation of shipping order {} completed", shippingOrderId);
        } else {
            LOGGER.info("Inspection operation of shipping order {} does not exist", shippingOrderId);
            return false;
        }
        LOGGER.debug("checkMandatoryOperationsCompleted is returning {}",  bunkeringOperationCompleted && inspectionOperationCompleted);
        return bunkeringOperationCompleted && inspectionOperationCompleted;
    }


}
