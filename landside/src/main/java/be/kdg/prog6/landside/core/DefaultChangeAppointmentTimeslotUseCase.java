package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.ports.in.ChangeAppointmentTimeslotUseCase;
import be.kdg.prog6.landside.ports.out.AppointmentLoadPort;
import be.kdg.prog6.landside.ports.out.AppointmentUpdatePort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultChangeAppointmentTimeslotUseCase implements ChangeAppointmentTimeslotUseCase {

    private final AppointmentUpdatePort appointmentUpdatePort;
    private final AppointmentLoadPort appointmentLoadPort;

    public DefaultChangeAppointmentTimeslotUseCase(AppointmentUpdatePort appointmentUpdatePort, AppointmentLoadPort appointmentLoadPort) {
        this.appointmentUpdatePort = appointmentUpdatePort;
        this.appointmentLoadPort = appointmentLoadPort;
    }

    @Override
    public Appointment changeAppointmentTimeslot(UUID appointmentId, LocalDateTime newTimeSlot) {
        Optional<Appointment> appOptional = appointmentLoadPort.loadAppointment(appointmentId);
        if (appOptional.isPresent()){
            Appointment app= appOptional.get();
            app.setTimeSlotStart(newTimeSlot);
            appointmentUpdatePort.updateAppointment(app);
        }
        return null;
    }
}
