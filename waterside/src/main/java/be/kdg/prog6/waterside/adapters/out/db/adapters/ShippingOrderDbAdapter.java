package be.kdg.prog6.waterside.adapters.out.db.adapters;

import be.kdg.prog6.waterside.adapters.out.db.entities.BunkeringOperationJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.entities.InspectionOperationJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.entities.ShippingOrderJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.springDataRepositories.SpringDataBunkeringOperationJpaRepository;
import be.kdg.prog6.waterside.adapters.out.db.springDataRepositories.SpringDataInspectionOperationJpaRepository;
import be.kdg.prog6.waterside.adapters.out.db.springDataRepositories.SpringDataShippingOrderJpaRepository;
import be.kdg.prog6.waterside.domain.ShippingOrder;
import be.kdg.prog6.waterside.ports.out.ShippingOrderCreatePort;
import be.kdg.prog6.waterside.ports.out.ShippingOrderLoadPort;
import be.kdg.prog6.waterside.ports.out.ShippingOrderUpdatePort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ShippingOrderDbAdapter implements ShippingOrderCreatePort, ShippingOrderLoadPort, ShippingOrderUpdatePort {
    SpringDataShippingOrderJpaRepository shippingOrderJpaRepository;

    public ShippingOrderDbAdapter(SpringDataShippingOrderJpaRepository shippingOrderJpaRepository) {
        this.shippingOrderJpaRepository = shippingOrderJpaRepository;
    }

    private void saveShippingOrder(ShippingOrder so){
        ShippingOrderJpaEntity soJPA = new ShippingOrderJpaEntity(
                so.getId(),
                so.getPurchaseOrderNo(),
                so.getShippingOrderNumber(),
                so.getVesselNumber(),
                so.getStatus()
        );
        shippingOrderJpaRepository.save(soJPA);
    }

    @Override
    public void shippingOrderCreated(ShippingOrder so) {
        saveShippingOrder(so);
    }

    @Override
    public Optional<ShippingOrder> loadShippingOrder(UUID shippingOrderId) {
        Optional<ShippingOrderJpaEntity> soJpaOptional = shippingOrderJpaRepository.findById(shippingOrderId);
        if (soJpaOptional.isPresent()){
            ShippingOrderJpaEntity soJpa = soJpaOptional.get();
            return Optional.of(new ShippingOrder(
                    soJpa.getId(),
                    soJpa.getShippingOrderNo(),
                    soJpa.getPurchaseOrderNo(),
                    soJpa.getVesselNumber(),
                    soJpa.getShippingOrderStatus()
            ));
        }
        return Optional.empty();
    }

    @Override
    public void updateShippingOrder(ShippingOrder so) {
        saveShippingOrder(so);
    }

    @Override
    public Optional<ShippingOrder> loadShippingOrderByPurchaseOrderNumber(String purchaseOrderNumber) {
        Optional<ShippingOrderJpaEntity> soJpaOptional = shippingOrderJpaRepository.findByPurchaseOrderNo(purchaseOrderNumber);
        if (soJpaOptional.isPresent()){
            ShippingOrderJpaEntity soJpa = soJpaOptional.get();
            return Optional.of(new ShippingOrder(
                    soJpa.getId(),
                    soJpa.getShippingOrderNo(),
                    soJpa.getPurchaseOrderNo(),
                    soJpa.getVesselNumber(),
                    soJpa.getShippingOrderStatus()
            ));
        }
        return Optional.empty();
    }
}
