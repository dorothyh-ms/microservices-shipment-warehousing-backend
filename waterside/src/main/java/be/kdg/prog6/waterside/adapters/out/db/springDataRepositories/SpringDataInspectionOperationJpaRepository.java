package be.kdg.prog6.waterside.adapters.out.db.springDataRepositories;


import be.kdg.prog6.waterside.adapters.out.db.entities.BunkeringOperationJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.entities.InspectionOperationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataInspectionOperationJpaRepository extends JpaRepository<InspectionOperationJpaEntity, UUID> {
 public List<InspectionOperationJpaEntity> findAllByInspectionDateIsNullAndInspectorSignatureIsNull();

 Optional<InspectionOperationJpaEntity> findFirstByShippingOrder_Id(UUID id);
}
