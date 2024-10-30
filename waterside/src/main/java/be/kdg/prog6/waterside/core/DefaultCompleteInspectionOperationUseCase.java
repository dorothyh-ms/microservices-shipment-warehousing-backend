package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.domain.InspectionOperation;
import be.kdg.prog6.waterside.exceptions.InspectionOperationNotFoundException;
import be.kdg.prog6.waterside.ports.in.CompleteInspectionOperationUseCase;
import be.kdg.prog6.waterside.ports.out.InspectionOperationLoadPort;
import be.kdg.prog6.waterside.ports.out.InspectionOperationUpdatePort;
import be.kdg.prog6.waterside.ports.out.LoadShipmentOntoVesselPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultCompleteInspectionOperationUseCase implements CompleteInspectionOperationUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCompleteInspectionOperationUseCase.class);

    private InspectionOperationLoadPort inspectionOperationLoadPort;
    private InspectionOperationUpdatePort inspectionOperationUpdatePort;
    private final LoadShipmentOntoVesselPort loadShipmentOntoVesselPort;

    public DefaultCompleteInspectionOperationUseCase(LoadShipmentOntoVesselPort loadShipmentOntoVesselPort, InspectionOperationUpdatePort inspectionOperationUpdatePort, InspectionOperationLoadPort inspectionOperationLoadPort) {
        this.loadShipmentOntoVesselPort = loadShipmentOntoVesselPort;
        this.inspectionOperationUpdatePort = inspectionOperationUpdatePort;
        this.inspectionOperationLoadPort = inspectionOperationLoadPort;
    }

    @Override
    public InspectionOperation completeInspectionOperation(UUID shippingOrderId, String signature) {
        LOGGER.info("DefaultCompleteInspectionOperationUseCase is running completeInspectionOperation with shipping order ID {} and signature {}", shippingOrderId, signature);
        Optional<InspectionOperation> ioOptional = inspectionOperationLoadPort.loadInspectionOperation(shippingOrderId);
        if (ioOptional.isEmpty()) {
            throw new InspectionOperationNotFoundException(String.format("Inspection operation for shipping order with id {} does not exist", shippingOrderId.toString()));
        }
        InspectionOperation io = ioOptional.get();
        io.setSignature(signature);
        io.setDateInspected(LocalDateTime.now());
        inspectionOperationUpdatePort.updateInspectionOperation(io);
        return io;
    }
}
