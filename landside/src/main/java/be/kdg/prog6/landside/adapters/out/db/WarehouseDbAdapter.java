package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class WarehouseDbAdapter implements WarehouseLoadPort {

    private final SpringDataWarehouseRepository warehouseRepository;

    public WarehouseDbAdapter(SpringDataWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
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
