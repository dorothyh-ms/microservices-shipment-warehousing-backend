package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import be.kdg.prog6.landside.core.DefaultGetWarehousesUseCase;
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
                .map(warehouse -> new Warehouse(warehouseId, warehouse.getSellerId(), warehouse.getMaterialId(), warehouse.getCurrentTons(), warehouse.getXCoord(), warehouse.getYCoord()))
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
    public List<Warehouse> loadWarehousesBySellerId(UUID sellerId) {
        List<WarehouseJPAEntity> warehouseJPAEntities = warehouseRepository.findWarehouseJPAEntitiesBySellerId(sellerId);
        return warehouseJPAEntities.stream().map(warehouseEntity -> new Warehouse(
                warehouseEntity.getId(),
                warehouseEntity.getSellerId(),
                warehouseEntity.getMaterialId(),
                warehouseEntity.getCurrentTons(),
                warehouseEntity.getXCoord(),
                warehouseEntity.getYCoord()
        )).toList();
    }

    @Override
    public List<Warehouse> loadWarehouses() {
        LOGGER.info("WarehouseProjectionDbAdapter is running loadWarehouses");
        return warehouseRepository.findAll().stream().map(warehouseJpaEntity -> new Warehouse(
                        warehouseJpaEntity.getId(),
                        warehouseJpaEntity.getSellerId(),
                        warehouseJpaEntity.getMaterialId(),
                        warehouseJpaEntity.getCurrentTons(),
                        warehouseJpaEntity.getXCoord(),
                        warehouseJpaEntity.getYCoord()
                )
        ).toList();
    }
}
