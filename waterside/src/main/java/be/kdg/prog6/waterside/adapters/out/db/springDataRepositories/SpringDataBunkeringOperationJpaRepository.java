package be.kdg.prog6.waterside.adapters.out.db.springDataRepositories;

import be.kdg.prog6.waterside.adapters.out.db.entities.BunkeringOperationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataBunkeringOperationJpaRepository extends JpaRepository<BunkeringOperationJpaEntity, UUID> {
    List<BunkeringOperationJpaEntity> findAllByPlannedDateEquals(LocalDate date);

    Optional<BunkeringOperationJpaEntity> findFirstByShippingOrder_Id(UUID id);

    List<BunkeringOperationJpaEntity> findByCompletedDateIsNull();
}
