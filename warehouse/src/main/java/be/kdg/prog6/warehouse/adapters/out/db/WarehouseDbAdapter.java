package be.kdg.prog6.warehouse.adapters.out.db;

import be.kdg.prog6.warehouse.adapters.in.WeighbridgeDepartureListener;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.warehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.warehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.warehouse.ports.out.WarehouseActivityCreatedPort;
import be.kdg.prog6.warehouse.domain.WarehouseActivity;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import main.java.be.kdg.prog6.warehouse.domain.Seller;
import main.java.be.kdg.prog6.warehouse.domain.Material;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class WarehouseDbAdapter implements LoadWarehousePort, UpdateWarehousePort, WarehouseActivityCreatedPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseDbAdapter.class);
    private final SpringDataWarehouseRepository warehouseRepository;

    private final SpringDataWarehouseActivityRepository warehouseActivityRepository;

    public WarehouseDbAdapter(SpringDataWarehouseRepository warehouseRepository, SpringDataWarehouseActivityRepository warehouseActivityRepository) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseActivityRepository = warehouseActivityRepository;
    }

    @Override
    public Optional<Warehouse> loadWarehouseById(UUID warehouseId) {

        final Optional<WarehouseJpaEntity> optionalWarehouseJpaEntity = warehouseRepository.findById(warehouseId);
        if (optionalWarehouseJpaEntity.isPresent()) {
            final WarehouseJpaEntity warehouseJpaEntity = optionalWarehouseJpaEntity.get();
            final MaterialJpaEntity materialJpaEntity = warehouseJpaEntity.getMaterial();
            final SellerJpaEntity sellerJpaEntity = warehouseJpaEntity.getSeller();
            Seller seller = new Seller(sellerJpaEntity.getId(), sellerJpaEntity.getName());
            Material material = new Material(materialJpaEntity.getId(), materialJpaEntity.getName());

            Warehouse warehouse = new Warehouse(
                    warehouseJpaEntity.getUuid(),
                    seller,
                    material,
                    new WarehouseActivityWindow()
            );


            List<WarehouseActivityJpaEntity> warehouseActivityJpaEntityList = null;
            if (warehouseJpaEntity.getBaseAmountTonsDate() != null) {
                warehouseActivityJpaEntityList = warehouseActivityRepository.findByWarehouseAndDateTimeGreaterThanEqual(warehouseJpaEntity, warehouseJpaEntity.getBaseAmountTonsDate());
            } else {
                warehouseActivityJpaEntityList = warehouseActivityRepository.findByWarehouse(warehouseJpaEntity);
            }

            for (WarehouseActivityJpaEntity warehouseActivityJpa : warehouseActivityJpaEntityList) {
                warehouse.addActivity(new WarehouseActivity(
                        warehouseActivityJpa.getActionType(),
                        material,
                        warehouseActivityJpa.getAmountTons(),
                        warehouseActivityJpa.getDateTime())
                );
            }


            return Optional.of(warehouse);

        }
        return Optional.empty();
    }


    @Override
    public void updateWarehouse(Warehouse warehouse) {
        WarehouseJpaEntity warehouseJpaEntity = new WarehouseJpaEntity(warehouse.getId());
        Seller seller = warehouse.getSeller();
        Material material = warehouse.getMaterial();
        warehouseJpaEntity.setSeller(new SellerJpaEntity(seller.getId(), seller.getName()));
        warehouseJpaEntity.setMaterial(new MaterialJpaEntity(material.getId(), material.getName()));
        warehouseJpaEntity.setBaseAmountTons(warehouse.getBaseAmountTons());
        warehouseJpaEntity.setBaseAmountTonsDate(warehouse.getBaseAmountTonsDate());
        warehouseRepository.save(warehouseJpaEntity);

    }


    @Override
    public void warehouseActivityCreated(UUID warehouseId, WarehouseActivity activity) {
        LOGGER.info("WarehouseDbAdapter running warehouseActivityCreated");
        LOGGER.info("Saving activity {} for warehouse with id {}", activity, warehouseId
        );
        WarehouseActivityJpaEntity warehouseActivityJpaEntity = new WarehouseActivityJpaEntity();
        WarehouseJpaEntity warehouse = warehouseRepository.findById(warehouseId).orElseThrow(EntityNotFoundException::new);
        warehouseActivityJpaEntity.setWarehouse(warehouse);
        warehouseActivityJpaEntity.setActionType(activity.action());
        warehouseActivityJpaEntity.setAmountTons(activity.amountTons());
        warehouseActivityJpaEntity.setDateTime(activity.activityDate());
        warehouseActivityRepository.save(warehouseActivityJpaEntity);
    }


}
