package be.kdg.prog6.landside.core;


import be.kdg.prog6.landside.domain.WeighBridgeTransaction;
import be.kdg.prog6.landside.domain.WeighbridgeMeasurement;
import be.kdg.prog6.landside.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.landside.ports.in.WeighbridgeDepartureUseCase;
import be.kdg.prog6.landside.ports.out.PayloadDeliveredPort;
import be.kdg.prog6.landside.ports.out.WeighbridgeTransactionEndedPort;
import be.kdg.prog6.landside.ports.out.WeighbridgeTransactionLoadPort;
import be.kdg.prog6.landside.ports.in.commands.FinalizeWeighBridgeTransactionCommand;
import be.kdg.prog6.landside.ports.out.DeliveryAppointmentLoadPort;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DefaultWeighBridgeDepartureUseCase implements WeighbridgeDepartureUseCase {

    private final DeliveryAppointmentLoadPort appointmentLoadPort;
    private final WeighbridgeTransactionLoadPort weighbridgeTransactionLoadPort;

    private final WeighbridgeTransactionEndedPort weighbridgeTransactionEndedPort;

    private final PayloadDeliveredPort payloadDeliveredPort;


    public DefaultWeighBridgeDepartureUseCase(DeliveryAppointmentLoadPort appointmentLoadPort, WeighbridgeTransactionLoadPort weighbridgeTransactionLoadPort, WeighbridgeTransactionEndedPort weighbridgeTransactionEndedPort, PayloadDeliveredPort payloadDeliveredPort) {
        this.appointmentLoadPort = appointmentLoadPort;
        this.weighbridgeTransactionLoadPort = weighbridgeTransactionLoadPort;
        this.weighbridgeTransactionEndedPort = weighbridgeTransactionEndedPort;
        this.payloadDeliveredPort = payloadDeliveredPort;
    }

    @Override
    public WeighBridgeTransaction recordTruckDepartureWeight(FinalizeWeighBridgeTransactionCommand finalizeWeighBridgeTransactionCommand) {
        //TODO: get existing weighbridge transaction

        Optional<WeighBridgeTransaction> weighBridgeTransactionOptional = weighbridgeTransactionLoadPort.loadWeighBridgeTransactionByLicensePlate(finalizeWeighBridgeTransactionCommand.truckLicensePlate());

        if (weighBridgeTransactionOptional.isEmpty()){
            throw new AppointmentNotFoundException("No appointment for this truck");
        }
        //TODO: set departure weight

        WeighBridgeTransaction weighBridgeTransaction = weighBridgeTransactionOptional.get();
        weighBridgeTransaction.setWeightOut(
                new WeighbridgeMeasurement(
                finalizeWeighBridgeTransactionCommand.amountTons(),
                LocalDateTime.now()
                )
        );

        weighbridgeTransactionEndedPort.weighbridgeTransactionEnded(weighBridgeTransaction);

        // TODO: publish delivery ticket created event

        payloadDeliveredPort.deliveryCompleted(weighBridgeTransaction);

        return weighBridgeTransaction;
    }
}
