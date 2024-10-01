package be.kdg.prog6.landside.adapters.in;

import be.kdg.prog6.landside.adapters.in.dtos.CreateWeighbridgeTransactionDto;
import be.kdg.prog6.landside.adapters.in.dtos.FinalizeWeighbridgeTransactionDto;
import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.ports.in.WeighbridgeArrivalUseCase;
import be.kdg.prog6.landside.ports.in.WeighbridgeDepartureUseCase;
import be.kdg.prog6.landside.ports.in.commands.CreateWeighBridgeTransactionCommand;
import be.kdg.prog6.landside.ports.in.commands.FinalizeWeighBridgeTransactionCommand;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/weighbridges")
public class WeighbridgeController {
    private final WeighbridgeArrivalUseCase weighbridgeArrivalUseCase;
    private final WeighbridgeDepartureUseCase weighbridgeDepartureUseCase;

    public WeighbridgeController(WeighbridgeArrivalUseCase weighbridgeArrivalUseCase, WeighbridgeDepartureUseCase weighbridgeDepartureUseCase) {
        this.weighbridgeArrivalUseCase = weighbridgeArrivalUseCase;
        this.weighbridgeDepartureUseCase = weighbridgeDepartureUseCase;
    }

    @PostMapping("/{licensePlate}/wbt")
    public void createWeighbridgeDeliveryTicket(
            @RequestBody CreateWeighbridgeTransactionDto createWeighbridgeTransactionDto,
            @PathVariable String licensePlate){

        System.out.println("arrived at weighbridge");
        weighbridgeArrivalUseCase.recordTruckArrivalWeight(
                new CreateWeighBridgeTransactionCommand(
                        licensePlate,
                        createWeighbridgeTransactionDto.getTruckInitialWeightTons()
                )
        );
    }

    @PatchMapping("/{licensePlate}/wbt")
    public void addEndWeightToWeighbridgeDeliveryTicket(
            @RequestBody FinalizeWeighbridgeTransactionDto finalizeWeighbridgeTransactionDto,
            @PathVariable String licensePlate){
            WeighBridgeTransaction weighBridgeTransaction = weighbridgeDepartureUseCase.recordTruckDepartureWeight(
                new FinalizeWeighBridgeTransactionCommand(
                        licensePlate,
                        finalizeWeighbridgeTransactionDto.getTruckFinalWeightTons()
                )
        );

    }
}
