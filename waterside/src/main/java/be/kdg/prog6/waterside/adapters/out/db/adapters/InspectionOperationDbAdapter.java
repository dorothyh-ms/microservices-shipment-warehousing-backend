package be.kdg.prog6.waterside.adapters.out.db.adapters;

import be.kdg.prog6.waterside.adapters.out.db.entities.InspectionOperationJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.entities.ShippingOrderJpaEntity;
import be.kdg.prog6.waterside.adapters.out.db.springDataRepositories.SpringDataInspectionOperationJpaRepository;
import be.kdg.prog6.waterside.domain.InspectionOperation;
import be.kdg.prog6.waterside.domain.ShippingOrder;
import be.kdg.prog6.waterside.ports.out.InspectionOperationCreatePort;
import be.kdg.prog6.waterside.ports.out.InspectionOperationLoadPort;
import be.kdg.prog6.waterside.ports.out.InspectionOperationUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InspectionOperationDbAdapter implements InspectionOperationCreatePort, InspectionOperationLoadPort, InspectionOperationUpdatePort {

    private static final Logger LOGGER = LoggerFactory.getLogger(InspectionOperationDbAdapter.class);

    private SpringDataInspectionOperationJpaRepository inspectionOperationJpaRepository;

    public InspectionOperationDbAdapter(SpringDataInspectionOperationJpaRepository inspectionOperationJpaRepository) {
        this.inspectionOperationJpaRepository = inspectionOperationJpaRepository;
    }

    @Override
    public List<InspectionOperation> loadInspectionOperations(boolean outstandingOnly) {
        LOGGER.info("InspectionOperationDbAdapter is running loadOutstandingInspectionOperations");
        List<InspectionOperationJpaEntity> inspectionOperationJpaEntities;

        if (outstandingOnly) {
            inspectionOperationJpaEntities = inspectionOperationJpaRepository.findAllByInspectionDateIsNullAndInspectorSignatureIsNull();
        } else {
            inspectionOperationJpaEntities = inspectionOperationJpaRepository.findAll();
        }
        return inspectionOperationJpaEntities.stream()
                .map(ioJpa -> {
                            ShippingOrderJpaEntity soJpa = ioJpa.getShippingOrder();
                            return new InspectionOperation(
                                    ioJpa.getId(),
                                    ioJpa.getInspectorSignature(),
                                    ioJpa.getInspectionDate(),
                                    new ShippingOrder(
                                            soJpa.getId(),
                                            soJpa.getShippingOrderNo(),
                                            soJpa.getPurchaseOrderNo(),
                                            soJpa.getVesselNumber(),
                                            soJpa.getShippingOrderStatus()
                                    )
                            );
                        }
                ).toList();
    }

    @Override
    public void inspectionOperationCreatedPort(InspectionOperation io) {
        saveInspectionOperation(io);
    }

    @Override
    public void updateInspectionOperation(InspectionOperation io) {
        saveInspectionOperation(io);
    }

    private void saveInspectionOperation(InspectionOperation io) {
        LOGGER.info("InspectionOperationDbAdapter is running saveInspectionOperation");
        ShippingOrder so = io.getShippingOrder();
        InspectionOperationJpaEntity ioJpa = new InspectionOperationJpaEntity(
                io.getId(),
                new ShippingOrderJpaEntity(
                        so.getId(),
                        so.getShippingOrderNumber(),
                        so.getPurchaseOrderNo(),
                        so.getVesselNumber(),
                        so.getStatus()
                )
        );
        ioJpa.setInspectionDate(io.getDateInspected());
        ioJpa.setInspectorSignature(io.getSignature());
        LOGGER.info("InspectionOperationDbAdapter is saving inspection operation jpa entity {}", ioJpa);
        inspectionOperationJpaRepository.save(ioJpa);
    }

    @Override
    public Optional<InspectionOperation> loadInspectionOperation(UUID shippingOrderId) {
        LOGGER.info("InspectionOperationDbAdapter is running loadInspectionOperation");
        Optional<InspectionOperationJpaEntity> ioJpaOptional = inspectionOperationJpaRepository.findFirstByShippingOrder_Id(shippingOrderId);
        if (ioJpaOptional.isPresent()) {
            InspectionOperationJpaEntity ioJpa = ioJpaOptional.get();
            ShippingOrderJpaEntity so = ioJpa.getShippingOrder();
            InspectionOperation io = new InspectionOperation(
                    ioJpa.getId(),
                    new ShippingOrder(
                            so.getId(),
                            so.getShippingOrderNo(),
                            so.getPurchaseOrderNo(),
                            so.getVesselNumber(),
                            so.getShippingOrderStatus()
                    )
            );
            io.setDateInspected(ioJpa.getInspectionDate());
            io.setSignature(ioJpa.getInspectorSignature());
            LOGGER.info("loadInspectionOperation is returning {}", io);
            return Optional.of(io);
        }
        LOGGER.info("loadInspectionOperation is returning an empty object");
        return Optional.empty();
    }
}
