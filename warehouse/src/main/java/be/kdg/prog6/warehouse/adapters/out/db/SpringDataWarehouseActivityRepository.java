package be.kdg.prog6.warehouse.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataWarehouseActivityRepository extends JpaRepository<WarehouseActivityJpaEntity, UUID> {
}
