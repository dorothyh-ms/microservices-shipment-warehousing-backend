package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.domain.ShippingOrder;
import be.kdg.prog6.waterside.domain.ShippingOrderStatus;
import be.kdg.prog6.waterside.exceptions.MandatoryOperationsNotCompletedException;
import be.kdg.prog6.waterside.exceptions.ShippingOrderNotFoundException;
import be.kdg.prog6.waterside.ports.in.LoadShipmentOntoVesselUseCase;
import be.kdg.prog6.waterside.ports.out.LoadShipmentOntoVesselPort;
import be.kdg.prog6.waterside.ports.out.ShippingOrderLoadPort;
import be.kdg.prog6.waterside.ports.out.ShippingOrderUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultLoadShipmentOntoVesselUseCase implements LoadShipmentOntoVesselUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLoadShipmentOntoVesselUseCase.class);
    private final MandatoryOperationsCompletedChecker mandatoryOperationsCompletedChecker;
    private final ShippingOrderLoadPort shippingOrderLoadPort;
    private final LoadShipmentOntoVesselPort loadShipmentOntoVesselPort;
    private final ShippingOrderUpdatePort shippingOrderUpdatePort;

    public DefaultLoadShipmentOntoVesselUseCase(MandatoryOperationsCompletedChecker mandatoryOperationsCompletedChecker, ShippingOrderLoadPort shippingOrderLoadPort, LoadShipmentOntoVesselPort loadShipmentOntoVesselPort, ShippingOrderUpdatePort shippingOrderUpdatePort) {
        this.mandatoryOperationsCompletedChecker = mandatoryOperationsCompletedChecker;
        this.shippingOrderLoadPort = shippingOrderLoadPort;
        this.loadShipmentOntoVesselPort = loadShipmentOntoVesselPort;
        this.shippingOrderUpdatePort = shippingOrderUpdatePort;
    }

    @Override
    public ShippingOrder loadShipmentOntoVessel(UUID shippingOrderId) {
        LOGGER.info("DefaultLoadShipmentOntoVesselUseCase is running loadShipmentOntoVessel for shipping order with id {}", shippingOrderId);
        Optional<ShippingOrder> soOptional = shippingOrderLoadPort.loadShippingOrder(shippingOrderId);
        if (soOptional.isEmpty()){
            LOGGER.error("Could not find shipping order with id {}", shippingOrderId);
            throw new ShippingOrderNotFoundException(String.format("Shipping order {} does not exist", shippingOrderId));
        }
        ShippingOrder so = soOptional.get();
        if (!mandatoryOperationsCompletedChecker.checkMandatoryOperationsCompleted(so.getId())){
            LOGGER.error("Bunkering or inspection operation not completed for shipping order with id {}", shippingOrderId);
            throw new MandatoryOperationsNotCompletedException(String.format("Bunkering and inspection operation must be completed to load shipment order {} onto vessel", shippingOrderId));
        };
        so.setStatus(ShippingOrderStatus.LOADING);
        shippingOrderUpdatePort.updateShippingOrder(so);
        loadShipmentOntoVesselPort.loadShipmentOntoVessel(so);
        LOGGER.info("loadShipmentOntoVessel is returning {}", so);
        return so;

    }
}
