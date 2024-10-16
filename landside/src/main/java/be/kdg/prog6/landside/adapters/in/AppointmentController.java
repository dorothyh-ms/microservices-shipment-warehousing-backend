package be.kdg.prog6.landside.adapters.in;


import be.kdg.prog6.landside.adapters.in.dtos.AppointmentDto;
import be.kdg.prog6.landside.adapters.in.dtos.CreateAppointmentDto;
import be.kdg.prog6.landside.ports.in.*;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import be.kdg.prog6.landside.ports.in.commands.ScanLicensePlateCommand;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase;

    private final GateArrivalUseCase gateArrivalUseCase;


    public AppointmentController(CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase, GateArrivalUseCase gateArrivalUseCase) {
        this.createDeliveryAppointmentUseCase = createDeliveryAppointmentUseCase;
        this.gateArrivalUseCase = gateArrivalUseCase;
    }

//    @GetMapping
//    @PreAuthorize("hasAuthority('seller')")
//    public void getAppointmentsOfSeller(
//            @AuthenticationPrincipal Jwt token){
//
//        createDeliveryAppointmentUseCase.createAppointment(new CreateDeliveryAppointmentCommand(
//                        createAppointmentDto.getSellerUUID(),
//                        createAppointmentDto.getMaterialUUID(),
//                        createAppointmentDto.getTruckLicensePlate(),
//                        createAppointmentDto.getAmountTons(),
//                        createAppointmentDto.getTimeSlot()
//                )
//        );
//    }


    @PostMapping
    @PreAuthorize("hasAuthority('seller')")
    public void createAppointment(
            @AuthenticationPrincipal Jwt token,
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
    @PreAuthorize("hasAuthority('trucker')")
    public void arriveAtAppointment(
            @PathVariable String licensePlate
    ){

        gateArrivalUseCase.checkTruck(new ScanLicensePlateCommand(licensePlate));

    }

}
