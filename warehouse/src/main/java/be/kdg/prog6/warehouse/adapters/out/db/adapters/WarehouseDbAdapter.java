package be.kdg.prog6.warehouse.adapters.out.db.adapters;


import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.warehouse.adapters.out.db.entities.DeliveryJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.entities.SellerJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.entities.WarehouseActivityJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.entities.WarehouseJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories.SpringDataDeliveryRepository;
import be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories.SpringDataWarehouseActivityRepository;
import be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories.SpringDataWarehouseRepository;
import be.kdg.prog6.warehouse.domain.Delivery;
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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class WarehouseDbAdapter implements LoadWarehousePort, UpdateWarehousePort, WarehouseActivityCreatedPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseDbAdapter.class);
    private final SpringDataWarehouseRepository warehouseRepository;

    private final SpringDataWarehouseActivityRepository warehouseActivityRepository;
    private final SpringDataDeliveryRepository deliveryRepository;


    public WarehouseDbAdapter(SpringDataWarehouseRepository warehouseRepository, SpringDataWarehouseActivityRepository warehouseActivityRepository, SpringDataDeliveryRepository deliveryRepository) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseActivityRepository = warehouseActivityRepository;
        this.deliveryRepository = deliveryRepository;
    }

    private Warehouse mapWarehouseJpaEntityToWarehouse(WarehouseJpaEntity warehouseJpaEntity) {
        final SellerJpaEntity sellerJpaEntity = warehouseJpaEntity.getSeller();
        Seller seller = new Seller(sellerJpaEntity.getId(), sellerJpaEntity.getName());
        WarehouseActivityWindow waw = new WarehouseActivityWindow();

        List<WarehouseActivityJpaEntity> warehouseActivityJpaEntityList;
        if (warehouseJpaEntity.getBaseAmountTonsDate() != null) {
            warehouseActivityJpaEntityList = warehouseActivityRepository.findByWarehouseAndDateTimeGreaterThanEqual(warehouseJpaEntity, warehouseJpaEntity.getBaseAmountTonsDate());
        } else {
            warehouseActivityJpaEntityList = warehouseActivityRepository.findByWarehouse(warehouseJpaEntity);
        }

        for (WarehouseActivityJpaEntity warehouseActivityJpa : warehouseActivityJpaEntityList) {
            System.out.println(warehouseActivityJpa);
            waw.addActivity(new WarehouseActivity(
                    warehouseActivityJpa.getActionType(),
                    warehouseJpaEntity.getMaterial(),
                    warehouseActivityJpa.getAmountTons(),
                    warehouseActivityJpa.getDateTime())
            );
        }

        Warehouse warehouse = new Warehouse(
                warehouseJpaEntity.getUuid(),
                seller,
                warehouseJpaEntity.getMaterial(),
                waw,
                new ArrayList<>(deliveryRepository.findByWarehouse(warehouseJpaEntity)
                        .stream()
                        .map(deliveryJpaEntity -> new Delivery(
                                deliveryJpaEntity.getId(),
                                deliveryJpaEntity.getDeliveredDateTime(),
                                deliveryJpaEntity.getMaterial(),
                                deliveryJpaEntity.getAmountTons()))
                        .toList()));
        LOGGER.info("mapWarehouseJpaEntityToWarehouse is returning warehouse {}", warehouse);
        return warehouse;

    }

    @Override
    public Optional<Warehouse> loadWarehouseById(UUID warehouseId) {

        final Optional<WarehouseJpaEntity> optionalWarehouseJpaEntity = warehouseRepository.findById(warehouseId);
        if (optionalWarehouseJpaEntity.isPresent()) {
            final WarehouseJpaEntity warehouseJpaEntity = optionalWarehouseJpaEntity.get();
            Warehouse warehouse = mapWarehouseJpaEntityToWarehouse(warehouseJpaEntity);
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
        warehouseJpaEntity.setMaterial(material);
        warehouseJpaEntity.setBaseAmountTons(warehouse.getBaseAmountTons());
        warehouseJpaEntity.setBaseAmountTonsDate(warehouse.getBaseAmountTonsDate());
        warehouseJpaEntity.setDeliveries(warehouse.getDeliveries().stream().map(delivery -> new DeliveryJpaEntity(
                delivery.getId(),
                delivery.getDeliveredDateTime(),
                delivery.getMaterial(),
                delivery.getAmountTons(),
                warehouseJpaEntity
        )).toList());

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

    @Override
    public Optional<Warehouse> loadWarehouseBySellerIdAndMaterial(UUID sellerId, Material material) {
        Optional<WarehouseJpaEntity> whJpaOptional = warehouseRepository.findFirstBySeller_IdAndMaterial(sellerId, material);
        if (whJpaOptional.isPresent()) {
            WarehouseJpaEntity whJpa = whJpaOptional.get();
            return Optional.of(mapWarehouseJpaEntityToWarehouse(whJpa));
        }
        return Optional.empty();
    }
}
