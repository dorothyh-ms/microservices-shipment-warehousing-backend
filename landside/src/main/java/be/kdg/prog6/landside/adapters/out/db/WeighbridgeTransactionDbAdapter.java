package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.DeliveryAppointmentJPAEntity;
import be.kdg.prog6.landside.adapters.out.db.entities.WeighbridgeTransactionJPAEntity;
import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.landside.exceptions.WeighbridgeTransactionNotFound;
import be.kdg.prog6.landside.ports.out.WeighbridgeTransactionEndedPort;
import be.kdg.prog6.landside.ports.out.WeighbridgeTransactionLoadPort;
import be.kdg.prog6.landside.ports.out.WeighbridgeTransactionStartedPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WeighbridgeTransactionDbAdapter implements WeighbridgeTransactionStartedPort, WeighbridgeTransactionEndedPort, WeighbridgeTransactionLoadPort {

    private final SpringDataWeighbridgeTransactionRepository weighbridgeTransactionRepository;
    private final SpringDataAppointmentRepository appointmentRepository;


    public WeighbridgeTransactionDbAdapter(SpringDataWeighbridgeTransactionRepository weighbridgeTransactionRepository, SpringDataAppointmentRepository appointmentRepository) {
        this.weighbridgeTransactionRepository = weighbridgeTransactionRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public WeighBridgeTransaction weighbridgeTransactionStarted(WeighBridgeTransaction transaction) {
        WeighbridgeTransactionJPAEntity transactionEntity = new WeighbridgeTransactionJPAEntity();
        Optional<DeliveryAppointmentJPAEntity> appointmentOptional = appointmentRepository.findById(transaction.getAppointment().getId());
        if (appointmentOptional.isEmpty()) {
            System.out.println("No appointment");
            throw new AppointmentNotFoundException("No appointment for truck at weighbridge");
        }
        DeliveryAppointmentJPAEntity appointmentJPAEntity = appointmentOptional.get();
        transactionEntity.setArrivalWeightTons(transaction.getWeightIn().weightTons());
        transactionEntity.setArrivalDateTime(transaction.getWeightIn().weighDateTime());
        transactionEntity.setDeliveryAppointment(appointmentJPAEntity);
        weighbridgeTransactionRepository.save(transactionEntity);
        return transaction;
    }

    @Override
    public Optional<WeighBridgeTransaction> loadWeighBridgeTransactionByLicensePlate(String licensePlate) {
        Optional<DeliveryAppointmentJPAEntity> appointmentOptional = appointmentRepository.findFirstByTruckLicensePlateAndStatusOrderByDateDescHour(licensePlate, AppointmentStatus.IN_PROGRESS);
        if (!appointmentOptional.isPresent()){
            throw new AppointmentNotFoundException("WBT does not have appointment");
        }
        DeliveryAppointmentJPAEntity appointmentJPAEntity = appointmentOptional.get();
        Appointment appointment = new Appointment(
                appointmentJPAEntity.getId(),
                appointmentJPAEntity.getMaterialUUID(),
                appointmentJPAEntity.getTruckLicensePlate(),
                appointmentJPAEntity.getDate().atTime(appointmentJPAEntity.getHour(), 0),
                appointmentJPAEntity.getStatus(),
                        new Warehouse(
                                appointmentJPAEntity.getAssignedWarehouse().getId(),
                                appointmentJPAEntity.getAssignedWarehouse().getSellerId(),
                                appointmentJPAEntity.getAssignedWarehouse().getMaterialId(),
                                appointmentJPAEntity.getAssignedWarehouse().getCurrentTons()
                        )
                        );
        return appointmentRepository.findFirstByTruckLicensePlateAndStatusOrderByDateDescHour(licensePlate, AppointmentStatus.IN_PROGRESS)
                .flatMap(weighbridgeTransactionRepository::findFirstByDeliveryAppointment)
                .map(trans -> new WeighBridgeTransaction(
                        trans.getId(),
                        appointment,
                        new WeighbridgeMeasurement(
                                trans.getArrivalWeightTons(),
                                trans.getArrivalDateTime()
                        ),
                        new WeighbridgeMeasurement(
                                trans.getDepartureWeightTons(),
                                trans.getDepartureDateTime()
                        )
                ));
    }

    @Override
    public WeighBridgeTransaction weighbridgeTransactionEnded(WeighBridgeTransaction transaction) {
        Optional<WeighbridgeTransactionJPAEntity> optionalWeighbridgeTransactionJPAEntity = weighbridgeTransactionRepository.findById(transaction.getId());
        if (optionalWeighbridgeTransactionJPAEntity.isEmpty()) {
            throw new WeighbridgeTransactionNotFound("Weighbridge transaction does not exist");
        }
        WeighbridgeTransactionJPAEntity weighbridgeTransactionJPAEntity = optionalWeighbridgeTransactionJPAEntity.get();
        weighbridgeTransactionJPAEntity.setDepartureDateTime(transaction.getWeightOut().weighDateTime());
        weighbridgeTransactionJPAEntity.setDepartureWeightTons(transaction.getWeightOut().weightTons());
        System.out.println("saving weighbridge transaction");
        weighbridgeTransactionRepository.save(weighbridgeTransactionJPAEntity);
        return transaction;
    }
}
