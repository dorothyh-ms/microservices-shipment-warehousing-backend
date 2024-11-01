package be.kdg.prog6.landside.ports.out;

import be.kdg.prog6.landside.domain.Appointment;

public interface AppointmentUpdatePort {
    public void updateAppointment(Appointment appointment);
}
