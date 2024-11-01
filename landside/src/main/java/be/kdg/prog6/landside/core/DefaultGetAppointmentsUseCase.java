package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.ports.in.GetAppointmentsUseCase;
import be.kdg.prog6.landside.ports.out.AppointmentLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetAppointmentsUseCase implements GetAppointmentsUseCase {
    private final AppointmentLoadPort appointmentLoadPort;

    public DefaultGetAppointmentsUseCase(AppointmentLoadPort appointmentLoadPort) {
        this.appointmentLoadPort = appointmentLoadPort;
    }

    public List<Appointment> getAppointments(){
        return appointmentLoadPort.loadAppointments();
    };
}
