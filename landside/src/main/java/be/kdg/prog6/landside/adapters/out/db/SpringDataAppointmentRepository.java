package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.DeliveryAppointmentJPAEntity;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataAppointmentRepository extends JpaRepository<DeliveryAppointmentJPAEntity, UUID> {
    Optional<DeliveryAppointmentJPAEntity> findFirstByTruckLicensePlateAndDateAndHour(String licensePlate, LocalDate date, int hour);


    Optional<DeliveryAppointmentJPAEntity> findFirstByTruckLicensePlateAndStatusOrderByDateDescHour(String licensePlate, AppointmentStatus status);


}
