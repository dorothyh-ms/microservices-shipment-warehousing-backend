package be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.warehouse.adapters.out.db.entities.WarehouseJpaEntity;
import be.kdg.prog6.warehouse.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataWarehouseRepository extends JpaRepository<WarehouseJpaEntity, UUID> {

    public Optional<WarehouseJpaEntity> findFirstBySeller_IdAndMaterial(UUID sellerId, Material material);
}
