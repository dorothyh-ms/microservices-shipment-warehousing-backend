package be.kdg.prog6.waterside.adapters.in.web;


import be.kdg.prog6.waterside.adapters.in.web.dtos.CompleteInspectionOperationDto;
import be.kdg.prog6.waterside.adapters.in.web.dtos.InspectionOperationDto;
import be.kdg.prog6.waterside.domain.InspectionOperation;
import be.kdg.prog6.waterside.ports.in.CompleteInspectionOperationUseCase;
import be.kdg.prog6.waterside.ports.in.GetInspectionOperationsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inspection-operations")
public class InspectionOperationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InspectionOperationController.class);

    private final GetInspectionOperationsUseCase getInspectionOperationsUseCase;
    private final CompleteInspectionOperationUseCase completeInspectionOperationUseCase;

    public InspectionOperationController(GetInspectionOperationsUseCase getInspectionOperationsUseCase, CompleteInspectionOperationUseCase completeInspectionOperationUseCase) {
        this.getInspectionOperationsUseCase = getInspectionOperationsUseCase;
        this.completeInspectionOperationUseCase = completeInspectionOperationUseCase;
    }

    @GetMapping
    public ResponseEntity<List<InspectionOperationDto>> getInspectionOperations(@RequestParam boolean outstandingOnly) {
        LOGGER.info("InspectionOperationController is running getOutstandingInspectionOperations");
        List<InspectionOperation> ios = getInspectionOperationsUseCase.getInspectionOperations(outstandingOnly);
        return new ResponseEntity<>(ios.stream().map(io ->
                new InspectionOperationDto(
                        io.getId(),
                        io.getShippingOrder().getVesselNumber(),
                        io.getDateInspected(),
                        io.getSignature()
                )).toList(),
        HttpStatus.OK);
    }

    @PatchMapping("/{shippingOrderId}")
    public ResponseEntity<InspectionOperationDto> completeInspectionOperation(@PathVariable UUID shippingOrderId, @RequestBody CompleteInspectionOperationDto completeInspectionOperationDto){
        LOGGER.info("InspectionOperationController is running completeInspectionOperation with shipping order ID {} and signature {}", shippingOrderId, completeInspectionOperationDto.getSignature());
        InspectionOperation io = completeInspectionOperationUseCase.completeInspectionOperation(shippingOrderId, completeInspectionOperationDto.getSignature());
        return new ResponseEntity<>(
                new InspectionOperationDto(
                        io.getId(),
                        io.getShippingOrder().getVesselNumber(),
                        io.getDateInspected(),
                        io.getSignature()
                )
        , HttpStatus.OK);
    }

}
