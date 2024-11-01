package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.domain.WeighbridgeMeasurement;
import be.kdg.prog6.landside.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.landside.ports.in.commands.CreateWeighBridgeTransactionCommand;
import be.kdg.prog6.landside.ports.in.WeighbridgeArrivalUseCase;
import be.kdg.prog6.landside.ports.out.AppointmentLoadPort;
import be.kdg.prog6.landside.ports.out.WeighbridgeTransactionStartedPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class DefaultWeighBridgeArrivalUseCase implements WeighbridgeArrivalUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWeighBridgeArrivalUseCase.class);
    private final AppointmentLoadPort appointmentLoadPort;
    private final WeighbridgeTransactionStartedPort weighbridgeTransactionStartedPort;

    public DefaultWeighBridgeArrivalUseCase(AppointmentLoadPort appointmentLoadPort, WeighbridgeTransactionStartedPort weighbridgeTransactionStartedPort) {
        this.appointmentLoadPort = appointmentLoadPort;
        this.weighbridgeTransactionStartedPort = weighbridgeTransactionStartedPort;
    }

    @Override
    public void recordTruckArrivalWeight(CreateWeighBridgeTransactionCommand createWeighBridgeTransactionCommand) {
        LOGGER.info("DefaultWeighBridgeArrivalUseCase is running recordTruckArrivalWeight with command {}", createWeighBridgeTransactionCommand);
        Optional<Appointment> appointment = appointmentLoadPort.loadAppointmentInProgressByLicensePlate(createWeighBridgeTransactionCommand.truckLicensePlate());
        if (!appointment.isPresent()){
            throw new AppointmentNotFoundException("Appointment does not exist");
        }
        WeighbridgeMeasurement arrivalMeasurement = new WeighbridgeMeasurement(
                createWeighBridgeTransactionCommand.amountTons(),
                LocalDateTime.now()
                );

        WeighBridgeTransaction transaction = new WeighBridgeTransaction(appointment.get(), arrivalMeasurement);
        LOGGER.info("recordTruckArrivalWeight started WBT {}", transaction);
        weighbridgeTransactionStartedPort.weighbridgeTransactionStarted(transaction);

    }
}
