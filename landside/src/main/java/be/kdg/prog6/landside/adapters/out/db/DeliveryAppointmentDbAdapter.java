package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.DeliveryAppointmentJPAEntity;
import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.out.DeliveryAppointmentLoadPort;
import be.kdg.prog6.landside.ports.out.DeliveryAppointmentBookedPort;
import be.kdg.prog6.landside.ports.out.TruckArrivedForDeliveryAppointmentPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class DeliveryAppointmentDbAdapter implements DeliveryAppointmentBookedPort, DeliveryAppointmentLoadPort, TruckArrivedForDeliveryAppointmentPort {

    private final SpringDataAppointmentRepository appointmentRepository;
    private final SpringDataWarehouseProjectionRepository warehouseRepository;

    public DeliveryAppointmentDbAdapter(SpringDataAppointmentRepository appointmentRepository, SpringDataWarehouseProjectionRepository warehouseRepository) {
        this.appointmentRepository = appointmentRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void deliveryAppointmentBooked(Appointment appointment) {
        Optional<WarehouseJPAEntity> warehouseJPAEntity = warehouseRepository.findById(appointment.getWarehouse().getId());
        appointmentRepository.save(new DeliveryAppointmentJPAEntity(
                appointment.getMaterialUUID(),
                appointment.getTruckLicensePlate(),
                appointment.getTimeSlotStart().toLocalDate(),
                appointment.getTimeSlotStart().getHour(),
                appointment.getStatus(),
                warehouseJPAEntity.get()
        ));

    }

    @Override
    public Optional<Appointment> loadAppointmentByLicensePlateAndArrivalTime(String licensePlate, LocalDateTime arrivalTime) {
        System.out.println(licensePlate);
        Optional<DeliveryAppointmentJPAEntity> appointmentJPAEntityOptional = appointmentRepository.findFirstByTruckLicensePlateAndDateAndHour(licensePlate, arrivalTime.toLocalDate(), arrivalTime.getHour());
        if (appointmentJPAEntityOptional.isPresent()){
            DeliveryAppointmentJPAEntity deliveryAppointmentJPAEntity = appointmentJPAEntityOptional.get();
            Appointment appointment = new Appointment(
                    deliveryAppointmentJPAEntity.getMaterialUUID(),
                    deliveryAppointmentJPAEntity.getTruckLicensePlate(),
                    deliveryAppointmentJPAEntity.getDate().atTime(deliveryAppointmentJPAEntity.getHour(), 0),
                    new Warehouse(
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getId(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getSellerId(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getMaterialId(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getCurrentTons()
                    )
            );
            appointment.setId(deliveryAppointmentJPAEntity.getId());
            return Optional.of(appointment);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Appointment> loadAppointmentInProgressByLicensePlate(String licensePlate) {
        Optional<DeliveryAppointmentJPAEntity> appointmentJPAEntityOptional = appointmentRepository.findFirstByTruckLicensePlateAndStatusOrderByDateDescHour(licensePlate, AppointmentStatus.IN_PROGRESS);
        if (appointmentJPAEntityOptional.isPresent()){
            DeliveryAppointmentJPAEntity deliveryAppointmentJPAEntity = appointmentJPAEntityOptional.get();
            Appointment appointment = new Appointment(
                    deliveryAppointmentJPAEntity.getMaterialUUID(),
                    deliveryAppointmentJPAEntity.getTruckLicensePlate(),
                    deliveryAppointmentJPAEntity.getDate().atTime(deliveryAppointmentJPAEntity.getHour(), 0),
                    new Warehouse(
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getId(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getSellerId(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getMaterialId(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getCurrentTons()
                    )
            );
            appointment.setId(deliveryAppointmentJPAEntity.getId());
            return Optional.of(appointment);
        }
        return Optional.empty();
    }

    @Override
    public void truckArrivedAtGate(Appointment appointment) {
        Optional<DeliveryAppointmentJPAEntity> appointmentJPAEntityOptional = appointmentRepository.findById(appointment.getId());
        DeliveryAppointmentJPAEntity appointmentJPAEntity = appointmentJPAEntityOptional.get();
        appointmentJPAEntity.setStatus(appointment.getStatus());
        appointmentRepository.save(appointmentJPAEntity);
    }
}
