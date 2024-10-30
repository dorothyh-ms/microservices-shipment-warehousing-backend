package be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories;

import be.kdg.prog6.warehouse.adapters.out.db.entities.DeliveryJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.entities.WarehouseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataDeliveryRepository extends JpaRepository<DeliveryJpaEntity, UUID> {

    List<DeliveryJpaEntity> findByWarehouse(WarehouseJpaEntity warehouseJpaEntity);
}
