package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.DeliveryAppointmentJPAEntity;
import be.kdg.prog6.landside.adapters.out.db.entities.WeighbridgeTransactionJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataWeighbridgeTransactionRepository extends JpaRepository<WeighbridgeTransactionJPAEntity, UUID> {

    Optional<WeighbridgeTransactionJPAEntity> findFirstByDeliveryAppointment(DeliveryAppointmentJPAEntity appointmentJPAEntity);
}
