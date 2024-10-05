package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataWarehouseProjectionRepository extends JpaRepository<WarehouseJPAEntity, UUID> {

    List<WarehouseJPAEntity> findWarehouseJPAEntitiesBySellerId(UUID sellerId);
}
