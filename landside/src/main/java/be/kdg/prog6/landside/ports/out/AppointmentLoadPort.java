package be.kdg.prog6.landside.ports.out;

import be.kdg.prog6.landside.domain.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentLoadPort {

    public Optional<Appointment> loadAppointmentByLicensePlateAndArrivalTime(String licensePlate, LocalDateTime arrivalTime);

    public Optional<Appointment> loadAppointmentInProgressByLicensePlate(String licensePlate);

    public List<Appointment> loadAppointments();
}
