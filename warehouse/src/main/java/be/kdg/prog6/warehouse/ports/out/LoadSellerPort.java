package be.kdg.prog6.warehouse.ports.out;
import main.java.be.kdg.prog6.warehouse.domain.Seller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadSellerPort {
    Optional<Seller> loadSellerById(UUID id);

    List<Seller> loadSellers();
}
