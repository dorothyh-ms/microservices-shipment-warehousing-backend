package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class WarehouseProjectionDbAdapter implements WarehouseLoadPort, UpdateWarehousePort {

    private final SpringDataWarehouseProjectionRepository warehouseRepository;

    public WarehouseProjectionDbAdapter(SpringDataWarehouseProjectionRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Warehouse loadWarehouse(UUID warehouseId) {
        return warehouseRepository.findById(warehouseId)
                .map(warehouse -> new Warehouse(warehouseId, warehouse.getSellerId(), warehouse.getMaterialId(), warehouse.getCurrentTons()))
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
        List<WarehouseJPAEntity> warehouseJPAEntities =  warehouseRepository.findWarehouseJPAEntitiesBySellerId(sellerId);
        return warehouseJPAEntities.stream().map(warehouseEntity -> new Warehouse(
                warehouseEntity.getId(),
                warehouseEntity.getSellerId(),
                warehouseEntity.getMaterialId(),
                warehouseEntity.getCurrentTons()
        )).toList();
    }
}
