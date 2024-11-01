package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.adapters.in.amqp.RemoveMaterialForShipmentListener;
import be.kdg.prog6.warehouse.exceptions.SellerNotFoundException;
import be.kdg.prog6.warehouse.ports.in.CreatePurchaseOrderCommand;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.domain.PurchaseOrderStatus;
import be.kdg.prog6.warehouse.ports.in.CreatePurchaseOrderUseCase;


import be.kdg.prog6.warehouse.ports.out.LoadSellerPort;
import be.kdg.prog6.warehouse.ports.out.PurchaseOrderCreatedPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import main.java.be.kdg.prog6.warehouse.domain.Seller;
@Service
public class DefaultCreatePurchaseOrderUseCase implements CreatePurchaseOrderUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCreatePurchaseOrderUseCase.class);
    private final LoadSellerPort loadSellerPort;

    private final List<PurchaseOrderCreatedPort> purchaseOrderCreatePorts;

    public DefaultCreatePurchaseOrderUseCase(LoadSellerPort loadSellerPort, List<PurchaseOrderCreatedPort> purchaseOrderCreatePorts) {
        this.loadSellerPort = loadSellerPort;
        this.purchaseOrderCreatePorts = purchaseOrderCreatePorts;
    }

    @Override
    public void createPurchaseOrder(CreatePurchaseOrderCommand createPurchaseOrderCommand) {
        LOGGER.info("DefaultCreatePurchaseOrderUseCase is running createPurchaseOrder with command {}", createPurchaseOrderCommand);
        Optional<Seller> sellerOptional = loadSellerPort.loadSellerById(createPurchaseOrderCommand.sellerId());
        Seller seller;
        if (sellerOptional.isEmpty()) {
            throw new SellerNotFoundException();
        } else {
            seller = sellerOptional.get();
        }
        PurchaseOrder po = new PurchaseOrder(
                UUID.randomUUID(),
                createPurchaseOrderCommand.purchaseOrderNumber(),
                createPurchaseOrderCommand.buyerEnterpriseNumber(),
                seller,
                createPurchaseOrderCommand.orderLines(),
                LocalDateTime.now(),
                PurchaseOrderStatus.OUTSTANDING,
                createPurchaseOrderCommand.vesselNumber()
        );
        purchaseOrderCreatePorts.forEach(p -> p.purchaseOrderCreated(po));
    }
}
