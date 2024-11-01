package be.kdg.prog6.waterside.adapters.out.db.springDataRepositories;

import be.kdg.prog6.waterside.adapters.out.db.entities.ShippingOrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataShippingOrderJpaRepository extends JpaRepository<ShippingOrderJpaEntity, UUID> {
    Optional<ShippingOrderJpaEntity> findByShippingOrderNo(String shippingOrderNumber);

    Optional<ShippingOrderJpaEntity> findByPurchaseOrderNo(String purchaseOrderNo);
}
