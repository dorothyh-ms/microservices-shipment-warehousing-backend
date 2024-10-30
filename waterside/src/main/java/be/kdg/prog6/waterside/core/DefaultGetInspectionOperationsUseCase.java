package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.domain.InspectionOperation;
import be.kdg.prog6.waterside.ports.in.GetInspectionOperationsUseCase;
import be.kdg.prog6.waterside.ports.out.InspectionOperationLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DefaultGetInspectionOperationsUseCase implements GetInspectionOperationsUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCompleteBunkeringOperationUseCase.class);

    private InspectionOperationLoadPort inspectionOperationLoadPort;

    public DefaultGetInspectionOperationsUseCase(InspectionOperationLoadPort inspectionOperationLoadPort) {
        this.inspectionOperationLoadPort = inspectionOperationLoadPort;
    }

    @Override
    public List<InspectionOperation> getInspectionOperations(boolean outstandingOnly) {
        LOGGER.info("DefaultGetInspectionOperationsUseCase is running getOutstandingInspectionOperations - retrieving outstanding only {}", outstandingOnly);
        return inspectionOperationLoadPort.loadInspectionOperations(outstandingOnly);
    }
}
