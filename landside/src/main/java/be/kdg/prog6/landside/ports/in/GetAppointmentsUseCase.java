package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.landside.domain.Appointment;

import java.util.List;

public interface GetAppointmentsUseCase {

    List<Appointment> getAppointments();



}
