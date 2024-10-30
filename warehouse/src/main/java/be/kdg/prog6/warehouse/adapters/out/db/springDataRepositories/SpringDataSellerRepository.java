package be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories;

import be.kdg.prog6.warehouse.adapters.out.db.entities.SellerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataSellerRepository extends JpaRepository<SellerJpaEntity, UUID> {

}
