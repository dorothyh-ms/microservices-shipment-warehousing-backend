package be.kdg.prog6.landside.adapters.in.web;


import be.kdg.prog6.landside.adapters.in.web.dtos.AppointmentDto;
import be.kdg.prog6.landside.adapters.in.web.dtos.CreateAppointmentDto;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.ports.in.*;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import be.kdg.prog6.landside.ports.in.commands.ScanLicensePlateCommand;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
    private final CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase;
    private final GateArrivalUseCase gateArrivalUseCase;
    private final GetAppointmentsUseCase getAppointmentsUseCase;


    public AppointmentController(CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase, GateArrivalUseCase gateArrivalUseCase, GetAppointmentsUseCase getAppointmentsUseCase) {
        this.createDeliveryAppointmentUseCase = createDeliveryAppointmentUseCase;
        this.gateArrivalUseCase = gateArrivalUseCase;
        this.getAppointmentsUseCase = getAppointmentsUseCase;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> loadAppointments(){
        List<Appointment> apps = getAppointmentsUseCase.getAppointments();
        return new ResponseEntity<>(apps.stream().map(app -> new AppointmentDto(
                app.getId(),
                app.getMaterial(),
                app.getTruckLicensePlate(),
                app.getAmountTons(),
                app.getTimeSlotStart(),
                app.getStatus()
        )).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(
            @RequestBody CreateAppointmentDto createAppointmentDto){
        LOGGER.info("AppointmentController is running createAppointment");

        Appointment appointment = createDeliveryAppointmentUseCase.createAppointment(new CreateDeliveryAppointmentCommand(
                createAppointmentDto.getSellerName(),
                createAppointmentDto.getMaterial(),
                createAppointmentDto.getTruckLicensePlate(),
                createAppointmentDto.getAmountTons(),
                createAppointmentDto.getTimeSlot()
                )
        );
        return new ResponseEntity<>(new AppointmentDto(
                appointment.getId(),
                appointment.getMaterial(),
                appointment.getTruckLicensePlate(),
                appointment.getAmountTons(),
                appointment.getTimeSlotStart(),
                appointment.getStatus()
        ), HttpStatus.OK);
    }



    @PatchMapping("/{licensePlate}")
    public void arriveAtAppointment(
            @PathVariable String licensePlate
    ){
        LOGGER.info("AppointmentController is running arriveAtAppointment");
        gateArrivalUseCase.checkTruck(new ScanLicensePlateCommand(licensePlate));
    }

}
