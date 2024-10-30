package be.kdg.prog6.waterside.adapters.in.web;


import be.kdg.prog6.waterside.adapters.in.web.dtos.BunkeringOperationDto;
import be.kdg.prog6.waterside.adapters.in.web.dtos.PlanBunkeringOperationDto;
import be.kdg.prog6.waterside.domain.BunkeringOperation;
import be.kdg.prog6.waterside.ports.in.CompleteBunkeringOperationUseCase;
import be.kdg.prog6.waterside.ports.in.GetBunkeringOperationsUseCase;
import be.kdg.prog6.waterside.ports.in.PlanBunkeringOperationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bunkering-operations")
public class BunkeringOperationController {

    private final PlanBunkeringOperationUseCase planBunkeringOperationUseCase;
    private final CompleteBunkeringOperationUseCase completeBunkeringOperationUseCase;
    private final GetBunkeringOperationsUseCase getBunkeringOperationsUseCase;

    private static final Logger LOGGER = LoggerFactory.getLogger(BunkeringOperationController.class);

    public BunkeringOperationController(PlanBunkeringOperationUseCase planBunkeringOperationUseCase, CompleteBunkeringOperationUseCase completeBunkeringOperationUseCase, GetBunkeringOperationsUseCase getBunkeringOperationsUseCase) {
        this.planBunkeringOperationUseCase = planBunkeringOperationUseCase;
        this.completeBunkeringOperationUseCase = completeBunkeringOperationUseCase;
        this.getBunkeringOperationsUseCase = getBunkeringOperationsUseCase;
    }

    @GetMapping
    public ResponseEntity<List<BunkeringOperationDto>> getBunkeringOperations(@RequestParam boolean outstandingOnly) {
        LOGGER.info("BunkeringOperationController is running getBunkeringOperations");
        List<BunkeringOperation> bos = getBunkeringOperationsUseCase.getBunkeringOperations(outstandingOnly);
        return new ResponseEntity<>(
                bos.stream().map(bo -> new BunkeringOperationDto(
                        bo.getId(),
                        bo.getShippingOrder().getVesselNumber(),
                        bo.getDateCompleted(),
                        bo.getDatePlanned()
                )).toList(),
                HttpStatus.OK);
    }

    //NOT RESTFUL!
    @PatchMapping("/{shippingOrderId}/plan")
    public ResponseEntity<BunkeringOperationDto> planBunkeringOperation(@PathVariable UUID shippingOrderId, @RequestBody PlanBunkeringOperationDto planDto) {
        LOGGER.info("BunkeringOperationController is running planBunkeringOperation with request body {}", planDto);
        BunkeringOperation bo = planBunkeringOperationUseCase.planBunkeringOperation(shippingOrderId, planDto.getPlannedDate());
        return new ResponseEntity<>(new BunkeringOperationDto(
                bo.getId(),
                bo.getShippingOrder().getVesselNumber(),
                bo.getDateCompleted(),
                bo.getDatePlanned()
        ), HttpStatus.OK);
    }

    //NOT RESTFUL!
    @PatchMapping("/{shippingOrderId}/complete")
    public ResponseEntity<BunkeringOperationDto> completeBunkeringOperation(@PathVariable UUID shippingOrderId) {
        LOGGER.info("BunkeringOperationController is running completeBunkeringOperation");
        BunkeringOperation bo = completeBunkeringOperationUseCase.completeBunkeringOperationOfShippingOrder(shippingOrderId);
        return new ResponseEntity<>(new BunkeringOperationDto(
                bo.getId(),
                bo.getShippingOrder().getVesselNumber(),
                bo.getDateCompleted(),
                bo.getDatePlanned()
        ), HttpStatus.OK);
    }


}
