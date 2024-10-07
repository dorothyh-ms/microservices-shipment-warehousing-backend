package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.ports.out.AppointmentBookedPort;

public class AppointmentBookedPortStub implements AppointmentBookedPort {

    private Appointment appointment;
    @Override
    public void appointmentBooked(Appointment appointment) {
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
