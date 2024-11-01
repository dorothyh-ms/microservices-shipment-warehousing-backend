package be.kdg.prog6.warehouse.adapters.out.db.adapters;

import be.kdg.prog6.warehouse.adapters.out.db.entities.SellerJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories.SpringDataSellerRepository;
import be.kdg.prog6.warehouse.ports.out.LoadSellerPort;
import main.java.be.kdg.prog6.warehouse.domain.Seller;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SellerDbAdapter implements LoadSellerPort {
    private final SpringDataSellerRepository sellerRepository;

    public SellerDbAdapter(SpringDataSellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Optional<Seller> loadSellerById(UUID id) {
        Optional<SellerJpaEntity> sellerJpaEntity = sellerRepository.findById(id);
        if (sellerJpaEntity.isPresent()){
            SellerJpaEntity sellerEntity = sellerJpaEntity.get();
            return Optional.of(
                    new Seller(sellerEntity.getId(), sellerEntity.getName())
            );
        }
        return Optional.empty();
    }

    @Override
    public List<Seller> loadSellers() {
        return sellerRepository.findAll().stream().map(sellerJpaEntity -> new Seller(sellerJpaEntity.getId(), sellerJpaEntity.getName())).toList();
    }
}
