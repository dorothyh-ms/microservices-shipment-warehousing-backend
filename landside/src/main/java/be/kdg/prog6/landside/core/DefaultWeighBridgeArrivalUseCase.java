package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.domain.WeighbridgeMeasurement;
import be.kdg.prog6.landside.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.landside.ports.in.commands.CreateWeighBridgeTransactionCommand;
import be.kdg.prog6.landside.ports.in.WeighbridgeArrivalUseCase;
import be.kdg.prog6.landside.ports.out.DeliveryAppointmentLoadPort;
import be.kdg.prog6.landside.ports.out.WeighbridgeTransactionStartedPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class DefaultWeighBridgeArrivalUseCase implements WeighbridgeArrivalUseCase {

    private final DeliveryAppointmentLoadPort deliveryAppointmentLoadPort;
    private final WeighbridgeTransactionStartedPort weighbridgeTransactionStartedPort;

    public DefaultWeighBridgeArrivalUseCase(DeliveryAppointmentLoadPort deliveryAppointmentLoadPort, WeighbridgeTransactionStartedPort weighbridgeTransactionStartedPort) {
        this.deliveryAppointmentLoadPort = deliveryAppointmentLoadPort;
        this.weighbridgeTransactionStartedPort = weighbridgeTransactionStartedPort;
    }

    @Override
    public void recordTruckArrivalWeight(CreateWeighBridgeTransactionCommand createWeighBridgeTransactionCommand) {
        Optional<Appointment> appointment = deliveryAppointmentLoadPort.loadAppointmentInProgressByLicensePlate(createWeighBridgeTransactionCommand.truckLicensePlate());
        if (!appointment.isPresent()){
            throw new AppointmentNotFoundException("Appointment does not exist");
        }
        WeighbridgeMeasurement arrivalMeasurement = new WeighbridgeMeasurement(
                createWeighBridgeTransactionCommand.amountTons(),
                LocalDateTime.now()
                );

        WeighBridgeTransaction transaction = new WeighBridgeTransaction(appointment.get(), arrivalMeasurement);
        weighbridgeTransactionStartedPort.weighbridgeTransactionStarted(transaction);

    }
}
