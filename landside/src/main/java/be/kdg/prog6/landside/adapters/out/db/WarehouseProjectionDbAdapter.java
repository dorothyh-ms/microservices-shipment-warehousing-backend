package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import be.kdg.prog6.landside.core.DefaultGetWarehousesUseCase;
import be.kdg.prog6.landside.domain.Seller;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class WarehouseProjectionDbAdapter implements WarehouseLoadPort, UpdateWarehousePort {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProjectionDbAdapter.class);
    private final SpringDataWarehouseProjectionRepository warehouseRepository;

    public WarehouseProjectionDbAdapter(SpringDataWarehouseProjectionRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Warehouse loadWarehouse(UUID warehouseId) {
        return warehouseRepository.findById(warehouseId)
                .map(warehouse -> new Warehouse(warehouseId,
                        new Seller(
                                warehouse.getSeller().getId(),
                                warehouse.getSeller().getName()
                        ),
                        warehouse.getMaterial(),
                        warehouse.getCurrentTons(),
                        warehouse.getXCoord(),
                        warehouse.getYCoord())
                )
                .orElseGet(() -> new Warehouse(warehouseId, 0));
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        final WarehouseJPAEntity warehouseJPAEntity = warehouseRepository.findById(warehouse.getId())
                .orElseGet(() -> new WarehouseJPAEntity(warehouse.getId()));
        warehouseJPAEntity.setCurrentTons(warehouse.getCurrentTons());
        warehouseRepository.save(warehouseJPAEntity);
    }

    @Override
    public List<Warehouse> loadWarehousesBySellerName(String sellerName) {
        List<WarehouseJPAEntity> warehouseJPAEntities = warehouseRepository.findWarehouseJPAEntitiesBySeller_Name(sellerName);
        return warehouseJPAEntities.stream().map(warehouseEntity -> new Warehouse(
                warehouseEntity.getId(),
                new Seller(
                        warehouseEntity.getSeller().getId(),
                        warehouseEntity.getSeller().getName()
                ),
                warehouseEntity.getMaterial(),
                warehouseEntity.getCurrentTons(),
                warehouseEntity.getXCoord(),
                warehouseEntity.getYCoord()
        )).toList();
    }

    @Override
    public List<Warehouse> loadWarehouses(String sellerName) {
        LOGGER.info("WarehouseProjectionDbAdapter is running loadWarehouses");
        List<WarehouseJPAEntity> warehouseJPAEntities;
        if (sellerName != null){
            warehouseJPAEntities = warehouseRepository.findWarehouseJPAEntitiesBySeller_Name(sellerName);
        } else {
            warehouseJPAEntities = warehouseRepository.findAll();
        }
        return warehouseJPAEntities.stream().map(warehouseJpaEntity -> new Warehouse(
                        warehouseJpaEntity.getId(),
                        new Seller(
                                warehouseJpaEntity.getSeller().getId(),
                                warehouseJpaEntity.getSeller().getName()
                        ),
                        warehouseJpaEntity.getMaterial(),
                        warehouseJpaEntity.getCurrentTons(),
                        warehouseJpaEntity.getXCoord(),
                        warehouseJpaEntity.getYCoord()
                )
        ).toList();
    }
}
