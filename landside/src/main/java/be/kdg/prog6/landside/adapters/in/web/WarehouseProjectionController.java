package be.kdg.prog6.landside.adapters.in.web;

import be.kdg.prog6.landside.adapters.in.web.dtos.WarehouseProjectionDto;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.ports.in.GetWarehousesUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouse-projections")
public class WarehouseProjectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProjectionController.class);
    private final GetWarehousesUseCase getWarehousesUseCase;

    public WarehouseProjectionController(GetWarehousesUseCase getWarehousesUseCase) {
        this.getWarehousesUseCase = getWarehousesUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('warehouse_manager')")
    public ResponseEntity<List<WarehouseProjectionDto>> getWarehouses(@AuthenticationPrincipal Jwt token) {
        LOGGER.info("WarehouseProjectionController is running getWarehouses");
        var warehouses = getWarehousesUseCase.getWarehouses();
        LOGGER.info("WarehouseProjectionController call getWarehouses returning {}", warehouses);
        return new ResponseEntity<>(
                warehouses.stream().map(
                        warehouse -> new WarehouseProjectionDto(
                                warehouse.getId(),
                                warehouse.getCurrentTons(),
                                warehouse.getXCoord(),
                                warehouse.getYCoord(),
                                Warehouse.WIDTH_METERS,
                                Warehouse.LENGTH_METERS,
                                Warehouse.MAX_CAPACITY_TONS,
                                warehouse.getMaterial()
                        )
                ).toList(),
                HttpStatus.OK
        );
    }
}
