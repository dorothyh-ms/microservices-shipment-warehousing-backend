package be.kdg.prog6.waterside.adapters.in.web;


import be.kdg.prog6.common.commands.CreateShipmentOrderCommand;
import be.kdg.prog6.waterside.adapters.in.web.dtos.NewShippingOrderDto;
import be.kdg.prog6.waterside.adapters.in.web.dtos.ShippingOrderDto;
import be.kdg.prog6.waterside.core.DefaultCreateShippingOrderUseCase;
import be.kdg.prog6.waterside.domain.ShippingOrder;
import be.kdg.prog6.waterside.ports.in.CreateShippingOrderUseCase;
import be.kdg.prog6.waterside.ports.in.LoadShipmentOntoVesselUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shipping-orders")
public class ShippingOrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCreateShippingOrderUseCase.class);

    private LoadShipmentOntoVesselUseCase loadShipmentOntoVesselUseCase;
    private CreateShippingOrderUseCase createShippingOrderUseCase;

    public ShippingOrderController(LoadShipmentOntoVesselUseCase loadShipmentOntoVesselUseCase, CreateShippingOrderUseCase createShippingOrderUseCase) {
        this.loadShipmentOntoVesselUseCase = loadShipmentOntoVesselUseCase;
        this.createShippingOrderUseCase = createShippingOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<ShippingOrderDto> createShippingOrder(@RequestBody NewShippingOrderDto newShippingOrder) {
        LOGGER.info("ShippingOrderController is running createShippingOrder with dto {}", newShippingOrder);
        ShippingOrder so = createShippingOrderUseCase.createShippingOrder(new CreateShipmentOrderCommand(
                newShippingOrder.getPurchaseOrderNo(),
                newShippingOrder.getShippingOrderNo(),
                newShippingOrder.getVesselNumber()
        ));
        return new ResponseEntity<>(
                new ShippingOrderDto(
                        so.getId(),
                        so.getPurchaseOrderNo(),
                        so.getShippingOrderNumber(),
                        so.getVesselNumber(),
                        so.getStatus().toString()
                ),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{shippingOrderId}")
    public ResponseEntity<ShippingOrderDto> loadVesselWithShipment(@PathVariable UUID shippingOrderId){
        LOGGER.info("ShippingOrderController is running loadVesselWithShipment for shipping order with id {}", shippingOrderId);
        ShippingOrder so = loadShipmentOntoVesselUseCase.loadShipmentOntoVessel(shippingOrderId);
        return new ResponseEntity<>(
                new ShippingOrderDto(
                        so.getId(),
                        so.getPurchaseOrderNo(),
                        so.getShippingOrderNumber(),
                        so.getVesselNumber(),
                        so.getStatus().toString()
                ),
                HttpStatus.OK
        );
    }
}
