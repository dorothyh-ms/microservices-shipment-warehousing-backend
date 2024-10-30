package be.kdg.prog6.landside.adapters.in.web;


import be.kdg.prog6.landside.adapters.in.web.dtos.CreateAppointmentDto;
import be.kdg.prog6.landside.ports.in.*;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import be.kdg.prog6.landside.ports.in.commands.ScanLicensePlateCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
    private final CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase;

    private final GateArrivalUseCase gateArrivalUseCase;


    public AppointmentController(CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase, GateArrivalUseCase gateArrivalUseCase) {
        this.createDeliveryAppointmentUseCase = createDeliveryAppointmentUseCase;
        this.gateArrivalUseCase = gateArrivalUseCase;
    }



    @PostMapping
    @PreAuthorize("hasAuthority('seller')")
    public void createAppointment(
            @AuthenticationPrincipal Jwt token,
            @RequestBody CreateAppointmentDto createAppointmentDto){
        LOGGER.info("AppointmentController is running createAppointment");

        createDeliveryAppointmentUseCase.createAppointment(new CreateDeliveryAppointmentCommand(
                createAppointmentDto.getSellerUUID(),
                createAppointmentDto.getMaterial(),
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
        LOGGER.info("AppointmentController is running arriveAtAppointment");
        gateArrivalUseCase.checkTruck(new ScanLicensePlateCommand(licensePlate));
    }

}
