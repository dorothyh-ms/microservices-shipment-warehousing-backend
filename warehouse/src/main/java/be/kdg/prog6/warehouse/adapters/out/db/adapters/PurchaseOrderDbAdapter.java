package be.kdg.prog6.warehouse.adapters.out.db.adapters;


import be.kdg.prog6.common.domain.PurchaseOrderLine;
import be.kdg.prog6.warehouse.adapters.out.db.entities.PurchaseOrderJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.entities.PurchaseOrderLineJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.entities.SellerJpaEntity;
import be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories.SpringDataPurchaseOrderLineRepository;
import be.kdg.prog6.warehouse.adapters.out.db.springDataRepositories.SpringDataPurchaseOrderRepository;
import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.ports.out.LoadPurchaseOrderPort;
import be.kdg.prog6.warehouse.ports.out.PurchaseOrderCreatedPort;
import be.kdg.prog6.warehouse.ports.out.PurchaseOrderFulfilledPort;
import main.java.be.kdg.prog6.warehouse.domain.Seller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class PurchaseOrderDbAdapter implements PurchaseOrderCreatedPort, LoadPurchaseOrderPort, PurchaseOrderFulfilledPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderDbAdapter.class);
    private final SpringDataPurchaseOrderRepository purchaseOrderRepository;
    private final SpringDataPurchaseOrderLineRepository purchaseOrderLineRepository;

    public PurchaseOrderDbAdapter(SpringDataPurchaseOrderRepository purchaseOrderRepository, SpringDataPurchaseOrderLineRepository purchaseOrderLineRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderLineRepository = purchaseOrderLineRepository;
    }

    private void savePurchaseOrder(PurchaseOrder po){
        PurchaseOrderJpaEntity poJPA = new PurchaseOrderJpaEntity(
                po.getId(),
                po.getPurchaseOrderNumber(),
                po.getOrderDateTime(),
                new SellerJpaEntity(po.getSeller().getId(), po.getSeller().getName()),
                po.getStatus(),
                po.getVesselNumber(),
                po.getBuyerEnterpriseNumber()
        );

        purchaseOrderRepository.save(poJPA);
        po.getOrderLines().forEach(pol -> purchaseOrderLineRepository.save(new PurchaseOrderLineJpaEntity(poJPA, pol.material(), pol.amountTons())));

    }

    @Override
    public void purchaseOrderCreated(PurchaseOrder po) {
        LOGGER.info("PurchaseOrderDbAdapter is running purchaseOrderCreated with purchase order {}", po);
        savePurchaseOrder(po);
    }

    @Override
    public List<PurchaseOrder> loadPurchaseOrders() {
        LOGGER.info("PurchaseOrderDbAdapter is running loadPurchaseOrders");
        List<PurchaseOrderJpaEntity> poJpas = purchaseOrderRepository.findAll();
        return poJpas.stream().map(poJpa -> new PurchaseOrder(
                poJpa.getId(),
                poJpa.getPurchaseOrderNumber(),
                poJpa.getBuyerEnterpriseNumber(),
                new Seller(poJpa.getSeller().getId(), poJpa.getSeller().getName()),
                purchaseOrderLineRepository.findByPurchaseOrder_Id(poJpa.getId())
                        .stream().map(polJpa -> new PurchaseOrderLine(polJpa.getMaterial(), polJpa.getAmountTons())).toList(),
                poJpa.getOrderDate(),
                poJpa.getStatus(),
                poJpa.getVesselNumber()

        )).toList();
    }

    @Override
    public Optional<PurchaseOrder> loadPurchaseOrderByPurchaseOrderNumber(String purchaseOrderNumber) {
        LOGGER.info("PurchaseOrderDbAdapter is running loadPurchaseOrderByPurchaseOrderNumber with purchase order number {}", purchaseOrderNumber);
        Optional<PurchaseOrderJpaEntity> poJpaOptional = purchaseOrderRepository.findByPurchaseOrderNumber(purchaseOrderNumber);
        if (poJpaOptional.isEmpty()){
            LOGGER.info("loadPurchaseOrderByPurchaseOrderNumber could not load a purchase order matching the purchase order number {}", purchaseOrderNumber);
            return Optional.empty();
        }
        PurchaseOrderJpaEntity poJpa = poJpaOptional.get();
        PurchaseOrder po = new PurchaseOrder(
                poJpa.getId(),
                poJpa.getPurchaseOrderNumber(),
                poJpa.getBuyerEnterpriseNumber(),
                new Seller(poJpa.getSeller().getId(), poJpa.getSeller().getName()),
                purchaseOrderLineRepository.findByPurchaseOrder_Id(poJpa.getId())
                        .stream().map(polJpa -> new PurchaseOrderLine(polJpa.getMaterial(), polJpa.getAmountTons())).toList(),
                poJpa.getOrderDate(),
                poJpa.getStatus(),
                poJpa.getVesselNumber()
        );
        LOGGER.info("loadPurchaseOrderByPurchaseOrderNumber is returning {}", po);
        return Optional.of(po);
    }

    @Override
    public void purchaseOrderFulfilled(PurchaseOrder po) {
        LOGGER.info("PurchaseOrderDbAdapter is running purchaseOrderFulfilled with purchase order {}", po);
        savePurchaseOrder(po);
    }
}
