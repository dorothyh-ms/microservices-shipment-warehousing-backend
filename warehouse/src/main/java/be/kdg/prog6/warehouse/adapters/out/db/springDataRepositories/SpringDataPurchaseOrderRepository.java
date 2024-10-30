package be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories;

import be.kdg.prog6.warehouse.adapters.out.db.entities.PurchaseOrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPurchaseOrderRepository extends JpaRepository<PurchaseOrderJpaEntity, UUID> {
    public Optional<PurchaseOrderJpaEntity> findByPurchaseOrderNumber(String purchaseOrderNumber);
}
