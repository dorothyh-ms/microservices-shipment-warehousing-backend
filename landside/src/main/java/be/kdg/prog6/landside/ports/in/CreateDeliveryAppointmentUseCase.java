package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;

public interface CreateDeliveryAppointmentUseCase {

    public Appointment createAppointment(CreateDeliveryAppointmentCommand createDeliveryAppointmentCommand);
}
