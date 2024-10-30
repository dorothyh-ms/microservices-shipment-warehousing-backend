package be.kdg.prog6.waterside.adapters.out.db.adapters;

import be.kdg.prog6.waterside.adapters.out.db.entities.BunkeringOperationJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.entities.InspectionOperationJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.entities.ShippingOrderJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.springDataRepositories.SpringDataBunkeringOperationJpaRepository;
import be.kdg.prog6.waterside.core.DefaultCreateShippingOrderUseCase;
import be.kdg.prog6.waterside.domain.BunkeringOperation;
import be.kdg.prog6.waterside.domain.BunkeringOperationDaySchedule;
import be.kdg.prog6.waterside.domain.InspectionOperation;
import be.kdg.prog6.waterside.domain.ShippingOrder;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationDayScheduleLoadPort;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationCreatePort;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationLoadPort;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BunkeringOperationDbAdapter implements BunkeringOperationCreatePort, BunkeringOperationUpdatePort, BunkeringOperationLoadPort, BunkeringOperationDayScheduleLoadPort {
    SpringDataBunkeringOperationJpaRepository bunkeringOperationJpaRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BunkeringOperationDbAdapter.class);


    public BunkeringOperationDbAdapter(SpringDataBunkeringOperationJpaRepository bunkeringOperationJpaRepository) {
        this.bunkeringOperationJpaRepository = bunkeringOperationJpaRepository;
    }

    @Override
    public List<BunkeringOperation> loadBunkeringOperations(boolean outstandingOnly) {
        List<BunkeringOperationJpaEntity> boJpas;
        if (outstandingOnly) {
            boJpas = bunkeringOperationJpaRepository.findByCompletedDateIsNull();
        } else {
            boJpas = bunkeringOperationJpaRepository.findAll();
        }
        return boJpas
                .stream()
                .map(boJpa -> {
                    ShippingOrderJpaEntity soJpa = boJpa.getShippingOrder();
                    return new BunkeringOperation(
                            boJpa.getId(),
                            new ShippingOrder(
                                    soJpa.getId(),
                                    soJpa.getShippingOrderNo(),
                                    soJpa.getPurchaseOrderNo(),
                                    soJpa.getVesselNumber(),
                                    soJpa.getShippingOrderStatus()
                            ),
                            boJpa.getCompletedDate(),
                            boJpa.getPlannedDate()
                    );
                })
                .toList();
    }

    @Override
    public void bunkeringOperationCreated(BunkeringOperation bo) {
        LOGGER.info("BunkeringOperationDbAdapter is running bunkeringOperationCreated");
        ShippingOrder so = bo.getShippingOrder();
        bunkeringOperationJpaRepository.save(
                new BunkeringOperationJpaEntity(
                        bo.getId(),
                        new ShippingOrderJpaEntity(
                                so.getId(),
                                so.getShippingOrderNumber(),
                                so.getPurchaseOrderNo(),
                                so.getVesselNumber(),
                                so.getStatus()
                        )));

    }

    @Override
    public void updateBunkeringOperation(BunkeringOperation bo) {
        LOGGER.info("BunkeringOperationDbAdapter is running updateBunkeringOperation");
        ShippingOrder so = bo.getShippingOrder();
        bunkeringOperationJpaRepository.save(
                new BunkeringOperationJpaEntity(
                        bo.getId(),
                        new ShippingOrderJpaEntity(
                                so.getId(),
                                so.getShippingOrderNumber(),
                                so.getPurchaseOrderNo(),
                                so.getVesselNumber(),
                                so.getStatus()
                        ),
                        bo.getDateCompleted(),
                        bo.getDatePlanned())
        );

    }

    @Override
    public Optional<BunkeringOperation> loadBunkeringOperationByShippingOrderId(UUID shippingOrderId) {
        LOGGER.info("BunkeringOperationDbAdapter is running loadBunkeringOperationByShippingOrderId");
        Optional<BunkeringOperationJpaEntity> boJpaOptional = bunkeringOperationJpaRepository.findFirstByShippingOrder_Id(shippingOrderId);
        if (boJpaOptional.isPresent()) {
            BunkeringOperationJpaEntity boJpa = boJpaOptional.get();
            ShippingOrderJpaEntity soJpa = boJpa.getShippingOrder();
            return Optional.of(
                    new BunkeringOperation(
                            boJpa.getId(),
                            new ShippingOrder(
                                    soJpa.getId(),
                                    soJpa.getShippingOrderNo(),
                                    soJpa.getPurchaseOrderNo(),
                                    soJpa.getVesselNumber(),
                                    soJpa.getShippingOrderStatus()
                            ),
                            boJpa.getCompletedDate(),
                            boJpa.getPlannedDate()
                    )
            );
        }
        return Optional.empty();
    }

    @Override
    public BunkeringOperationDaySchedule loadBunkeringOperationDaySchedule(LocalDate date) {
        LOGGER.info("BunkeringOperationDbAdapter is running loadBunkeringOperationDaySchedule");
        List<BunkeringOperationJpaEntity> bunkeringOperationJpaEntities = bunkeringOperationJpaRepository.findAllByPlannedDateEquals(date);
        BunkeringOperationDaySchedule daySchedule = new BunkeringOperationDaySchedule(date);
        List<BunkeringOperation> bunkeringOperations = (bunkeringOperationJpaEntities
                .stream()
                .map(boJpa -> {
                    ShippingOrderJpaEntity soJpa = boJpa.getShippingOrder();
                    return new BunkeringOperation(
                            boJpa.getId(),
                            new ShippingOrder(
                                    soJpa.getId(),
                                    soJpa.getShippingOrderNo(),
                                    soJpa.getPurchaseOrderNo(),
                                    soJpa.getVesselNumber(),
                                    soJpa.getShippingOrderStatus()
                            ),
                            boJpa.getCompletedDate(),
                            boJpa.getPlannedDate()
                    );
                }).toList());
        bunkeringOperations.forEach(daySchedule::planBunkeringOperation);
        return daySchedule;
    }
}
