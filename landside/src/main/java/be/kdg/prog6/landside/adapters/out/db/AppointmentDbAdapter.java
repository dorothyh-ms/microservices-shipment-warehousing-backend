package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.DeliveryAppointmentJPAEntity;
import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import be.kdg.prog6.landside.domain.Seller;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AppointmentDbAdapter implements AppointmentBookedPort, AppointmentLoadPort, TruckArrivedForDeliveryAppointmentPort, AppointmentUpdatePort, LoadDatesForAppointmentPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDbAdapter.class);
    private final SpringDataAppointmentRepository appointmentRepository;
    private final SpringDataWarehouseProjectionRepository warehouseRepository;

    public AppointmentDbAdapter(SpringDataAppointmentRepository appointmentRepository, SpringDataWarehouseProjectionRepository warehouseRepository) {
        this.appointmentRepository = appointmentRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Optional<Appointment> loadAppointment(UUID id) {
        Optional<DeliveryAppointmentJPAEntity> appointmentJpaOptional = appointmentRepository.findById(id);
        if (appointmentJpaOptional.isPresent()) {

            DeliveryAppointmentJPAEntity deliveryAppointmentJPAEntity = appointmentJpaOptional.get();
            Appointment appointment = new Appointment(
                    deliveryAppointmentJPAEntity.getMaterial(),
                    deliveryAppointmentJPAEntity.getTruckLicensePlate(),
                    deliveryAppointmentJPAEntity.getDate().atTime(deliveryAppointmentJPAEntity.getHour(), 0),
                    new Warehouse(
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getId(),
                            new Seller(
                                    deliveryAppointmentJPAEntity.getAssignedWarehouse().getSeller().getId(),
                                    deliveryAppointmentJPAEntity.getAssignedWarehouse().getSeller().getName()
                            ),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getMaterial(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getCurrentTons(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getXCoord(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getYCoord()
                    ),
                    deliveryAppointmentJPAEntity.getAmountTons()
            );
            appointment.setId(deliveryAppointmentJPAEntity.getId());
            return Optional.of(appointment);
        }
        return Optional.empty();
    }

    private void saveAppointment(Appointment appointment) {
        Optional<WarehouseJPAEntity> warehouseJPAEntity = warehouseRepository.findById(appointment.getWarehouse().getId());
        appointmentRepository.save(new DeliveryAppointmentJPAEntity(
                        appointment.getId(),
                        appointment.getMaterial(),
                        appointment.getTruckLicensePlate(),
                        appointment.getTimeSlotStart().toLocalDate(),
                        appointment.getTimeSlotStart().getHour(),
                        appointment.getStatus(),
                        warehouseJPAEntity.get(),
                        appointment.getAmountTons()
                )
        );
    }

    @Override
    public void appointmentBooked(Appointment appointment) {
        LOGGER.info("AppointmentDbAdapter is running appointmentBooked");
        saveAppointment(appointment);
    }

    @Override
    public Optional<Appointment> loadAppointmentByLicensePlateAndArrivalTime(String licensePlate, LocalDateTime arrivalTime) {
        System.out.println(licensePlate);
        Optional<DeliveryAppointmentJPAEntity> appointmentJPAEntityOptional = appointmentRepository.findFirstByTruckLicensePlateAndDateAndHour(licensePlate, arrivalTime.toLocalDate(), arrivalTime.getHour());
        if (appointmentJPAEntityOptional.isPresent()) {
            DeliveryAppointmentJPAEntity deliveryAppointmentJPAEntity = appointmentJPAEntityOptional.get();
            Appointment appointment = new Appointment(
                    deliveryAppointmentJPAEntity.getMaterial(),
                    deliveryAppointmentJPAEntity.getTruckLicensePlate(),
                    deliveryAppointmentJPAEntity.getDate().atTime(deliveryAppointmentJPAEntity.getHour(), 0),
                    new Warehouse(
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getId(),
                            new Seller(
                                    deliveryAppointmentJPAEntity.getAssignedWarehouse().getSeller().getId(),
                                    deliveryAppointmentJPAEntity.getAssignedWarehouse().getSeller().getName()
                            ),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getMaterial(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getCurrentTons(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getXCoord(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getYCoord()
                    ),
                    deliveryAppointmentJPAEntity.getAmountTons()
            );
            appointment.setId(deliveryAppointmentJPAEntity.getId());
            return Optional.of(appointment);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Appointment> loadAppointmentInProgressByLicensePlate(String licensePlate) {
        System.out.println("Loading real appointment");
        Optional<DeliveryAppointmentJPAEntity> appointmentJPAEntityOptional = appointmentRepository.findFirstByTruckLicensePlateAndStatusOrderByDateDescHour(licensePlate, AppointmentStatus.IN_PROGRESS);
        if (appointmentJPAEntityOptional.isPresent()) {
            DeliveryAppointmentJPAEntity deliveryAppointmentJPAEntity = appointmentJPAEntityOptional.get();
            Appointment appointment = new Appointment(
                    deliveryAppointmentJPAEntity.getMaterial(),
                    deliveryAppointmentJPAEntity.getTruckLicensePlate(),
                    deliveryAppointmentJPAEntity.getDate().atTime(deliveryAppointmentJPAEntity.getHour(), 0),
                    new Warehouse(
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getId(),
                            new Seller(
                                    deliveryAppointmentJPAEntity.getAssignedWarehouse().getSeller().getId(),
                                    deliveryAppointmentJPAEntity.getAssignedWarehouse().getSeller().getName()
                            ),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getMaterial(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getCurrentTons(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getXCoord(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getYCoord()
                    ),
                    deliveryAppointmentJPAEntity.getAmountTons()
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

    @Override
    public void updateAppointment(Appointment appointment) {
        saveAppointment(appointment);
    }

    @Override
    public List<Appointment> loadAppointments() {
        return appointmentRepository.findAll().stream().map(deliveryAppointmentJPAEntity -> {
            Appointment appointment = new Appointment(
                    deliveryAppointmentJPAEntity.getMaterial(),
                    deliveryAppointmentJPAEntity.getTruckLicensePlate(),
                    deliveryAppointmentJPAEntity.getDate().atTime(deliveryAppointmentJPAEntity.getHour(), 0),
                    new Warehouse(
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getId(),
                            new Seller(
                                    deliveryAppointmentJPAEntity.getAssignedWarehouse().getSeller().getId(),
                                    deliveryAppointmentJPAEntity.getAssignedWarehouse().getSeller().getName()
                            ),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getMaterial(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getCurrentTons(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getXCoord(),
                            deliveryAppointmentJPAEntity.getAssignedWarehouse().getYCoord()
                    ),
                    deliveryAppointmentJPAEntity.getAmountTons()

            );
            appointment.setId(deliveryAppointmentJPAEntity.getId());
            appointment.setStatus(deliveryAppointmentJPAEntity.getStatus());
            return appointment;
        }).toList();
    }

    @Override
    public List<LocalDate> loadDatesWithAppointmentNumberGreaterThanOrEqualTo(int count) {
        return appointmentRepository.findDatesWithMinAppointments(count);
    }
}
