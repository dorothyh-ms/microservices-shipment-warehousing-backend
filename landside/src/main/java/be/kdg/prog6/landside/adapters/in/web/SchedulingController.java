package be.kdg.prog6.landside.adapters.in.web;

import be.kdg.prog6.landside.adapters.in.web.dtos.AppointmentDto;
import be.kdg.prog6.landside.adapters.in.web.dtos.UpdateAppointmentDto;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.ports.in.ChangeAppointmentTimeslotUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/schedules")
public class SchedulingController {
    private final ChangeAppointmentTimeslotUseCase changeAppointmentTimeslotUseCase;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    public SchedulingController(ChangeAppointmentTimeslotUseCase changeAppointmentTimeslotUseCase) {
        this.changeAppointmentTimeslotUseCase = changeAppointmentTimeslotUseCase;
    }

    @PatchMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> updateAppointment(
            @RequestBody UpdateAppointmentDto updateAppointmentDto, @PathVariable UUID appointmentId) {
        LOGGER.info("AppointmentController is running updateAppointment");
        Appointment app = changeAppointmentTimeslotUseCase.changeAppointmentTimeslot(appointmentId, updateAppointmentDto.getTimeSlot());
        return new ResponseEntity<>(
                new AppointmentDto(
                        app.getId(),
                        app.getMaterial(),
                        app.getTruckLicensePlate(),
                        app.getAmountTons(),
                        app.getTimeSlotStart(),
                        app.getStatus()), HttpStatus.OK);
    }
}
