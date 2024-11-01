package be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories;

import be.kdg.prog6.warehouse.adapters.out.db.entities.PurchaseOrderJpaEntity;
import be.kdg.prog6.warehouse.domain.PurchaseOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPurchaseOrderRepository extends JpaRepository<PurchaseOrderJpaEntity, UUID> {
    public Optional<PurchaseOrderJpaEntity> findByPurchaseOrderNumber(String purchaseOrderNumber);
    public List<PurchaseOrderJpaEntity> findBySeller_Name(String sellerName);
    public List<PurchaseOrderJpaEntity> findByStatus(PurchaseOrderStatus purchaseOrderStatus);
    public List<PurchaseOrderJpaEntity> findByStatusAndSeller_Name(PurchaseOrderStatus purchaseOrderStatus, String sellerName);


}
