package be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories;

import be.kdg.prog6.warehouse.adapters.out.db.entities.WarehouseActivityJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.entities.WarehouseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataWarehouseActivityRepository extends JpaRepository<WarehouseActivityJpaEntity, UUID> {

    List<WarehouseActivityJpaEntity> findByWarehouseAndDateTimeGreaterThanEqual(WarehouseJpaEntity warehouse, LocalDateTime dateTime);

    List<WarehouseActivityJpaEntity> findByWarehouse(WarehouseJpaEntity warehouse);
}
