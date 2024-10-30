package be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories;

import be.kdg.prog6.common.domain.PurchaseOrderLine;
import be.kdg.prog6.warehouse.adapters.out.db.entities.PurchaseOrderLineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLineJpaEntity, UUID> {

    public List<PurchaseOrderLineJpaEntity> findByPurchaseOrder_Id(UUID id);
}
