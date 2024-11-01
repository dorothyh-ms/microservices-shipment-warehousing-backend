package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.SellerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataLandsideSellerRepository extends JpaRepository<SellerJpa, UUID> {
}
