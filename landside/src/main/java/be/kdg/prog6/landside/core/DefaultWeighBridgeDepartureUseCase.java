package be.kdg.prog6.landside.core;


import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.domain.WeighbridgeMeasurement;
import be.kdg.prog6.landside.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.landside.ports.in.WeighbridgeDepartureUseCase;
import be.kdg.prog6.landside.ports.out.*;
import be.kdg.prog6.landside.ports.in.commands.FinalizeWeighBridgeTransactionCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultWeighBridgeDepartureUseCase implements WeighbridgeDepartureUseCase {

    private final AppointmentUpdatePort appointmentUpdatePort;
    private final WeighbridgeTransactionLoadPort weighbridgeTransactionLoadPort;

    private final WeighbridgeTransactionEndedPort weighbridgeTransactionEndedPort;

    private final List<PayloadDeliveredPort> payloadDeliveredPorts;
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWeighBridgeDepartureUseCase.class);

    public DefaultWeighBridgeDepartureUseCase(AppointmentUpdatePort appointmentUpdatePort, WeighbridgeTransactionLoadPort weighbridgeTransactionLoadPort, WeighbridgeTransactionEndedPort weighbridgeTransactionEndedPort, List<PayloadDeliveredPort> payloadDeliveredPorts) {
        this.appointmentUpdatePort = appointmentUpdatePort;
        this.weighbridgeTransactionLoadPort = weighbridgeTransactionLoadPort;
        this.weighbridgeTransactionEndedPort = weighbridgeTransactionEndedPort;
        this.payloadDeliveredPorts = payloadDeliveredPorts;
    }

    @Override
    public WeighBridgeTransaction recordTruckDepartureWeight(FinalizeWeighBridgeTransactionCommand finalizeWeighBridgeTransactionCommand) {
        LOGGER.info("DefaultWeighBridgeDepartureUseCase is running recordTruckDepartureWeight with command {}", finalizeWeighBridgeTransactionCommand);


        Optional<WeighBridgeTransaction> weighBridgeTransactionOptional = weighbridgeTransactionLoadPort.loadWeighBridgeTransactionByLicensePlate(finalizeWeighBridgeTransactionCommand.truckLicensePlate());

        if (weighBridgeTransactionOptional.isEmpty()){
            throw new AppointmentNotFoundException("No appointment for this truck");
        }

        WeighBridgeTransaction weighBridgeTransaction = weighBridgeTransactionOptional.get();
        weighBridgeTransaction.setWeightOut(
                new WeighbridgeMeasurement(
                finalizeWeighBridgeTransactionCommand.amountTons(),
                LocalDateTime.now()
                )
        );
        Appointment appointment = weighBridgeTransaction.getAppointment();
        appointment.consolidate();

        weighbridgeTransactionEndedPort.weighbridgeTransactionEnded(weighBridgeTransaction);
        payloadDeliveredPorts.forEach(port ->port.deliveryCompleted(weighBridgeTransaction));
        appointmentUpdatePort.updateAppointment(appointment);
        return weighBridgeTransaction;
    }
}
