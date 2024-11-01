package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.landside.domain.Appointment;

import java.time.LocalDateTime;
import java.util.UUID;


public interface ChangeAppointmentTimeslotUseCase {

    public Appointment changeAppointmentTimeslot(UUID appointmentId, LocalDateTime newTimeSlot);
}
