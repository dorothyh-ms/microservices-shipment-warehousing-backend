package be.kdg.prog6.landside.adapters.in;


import be.kdg.prog6.landside.adapters.in.dtos.CreateAppointmentDto;
import be.kdg.prog6.landside.ports.in.*;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import be.kdg.prog6.landside.ports.in.commands.ScanLicensePlateCommand;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class DeliveryAppointmentController {
    private final CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase;

    private final GateArrivalUseCase gateArrivalUseCase;


    public DeliveryAppointmentController(CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase, GateArrivalUseCase gateArrivalUseCase) {
        this.createDeliveryAppointmentUseCase = createDeliveryAppointmentUseCase;
        this.gateArrivalUseCase = gateArrivalUseCase;
    }

    @PostMapping
    public void createAppointment(
            @RequestBody CreateAppointmentDto createAppointmentDto){

        createDeliveryAppointmentUseCase.createAppointment(new CreateDeliveryAppointmentCommand(
                createAppointmentDto.getSellerUUID(),
                createAppointmentDto.getMaterialUUID(),
                createAppointmentDto.getTruckLicensePlate(),
                createAppointmentDto.getAmountTons(),
                createAppointmentDto.getTimeSlot()
                )
        );
    }

    @PatchMapping("/{licensePlate}")
    public void arriveAtAppointment(
            @PathVariable String licensePlate
    ){
        System.out.println("checking truck");
        gateArrivalUseCase.checkTruck(new ScanLicensePlateCommand(licensePlate));

    }

}
