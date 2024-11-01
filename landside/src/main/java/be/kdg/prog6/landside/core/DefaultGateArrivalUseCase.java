package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.landside.ports.in.GateArrivalUseCase;
import be.kdg.prog6.landside.ports.in.commands.ScanLicensePlateCommand;
import be.kdg.prog6.landside.ports.out.AppointmentLoadPort;
import be.kdg.prog6.landside.ports.out.TruckArrivedForDeliveryAppointmentPort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DefaultGateArrivalUseCase implements GateArrivalUseCase {

    private final AppointmentLoadPort appointmentLoadPort;
    private final TruckArrivedForDeliveryAppointmentPort truckArrivedForDeliveryAppointmentPort;

    public DefaultGateArrivalUseCase(AppointmentLoadPort appointmentLoadPort, TruckArrivedForDeliveryAppointmentPort truckArrivedForDeliveryAppointmentPort) {
        this.appointmentLoadPort = appointmentLoadPort;
        this.truckArrivedForDeliveryAppointmentPort = truckArrivedForDeliveryAppointmentPort;
    }

    @Override
    public void checkTruck(ScanLicensePlateCommand checkTruckCommand) {

        Optional<Appointment> appointmentOptional = appointmentLoadPort.loadAppointmentByLicensePlateAndArrivalTime(
                checkTruckCommand.getLicensePlate(),
                checkTruckCommand.getArrivalTime()
        );
        if (appointmentOptional.isEmpty()){
            throw new AppointmentNotFoundException("No appointment for this truck could be found at this time");
        }
        Appointment appointment = appointmentOptional.get();
        appointment.inProgress();
        truckArrivedForDeliveryAppointmentPort.truckArrivedAtGate(appointment);
    }
}
