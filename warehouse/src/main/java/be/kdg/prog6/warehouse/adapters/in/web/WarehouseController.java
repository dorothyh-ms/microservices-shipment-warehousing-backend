package be.kdg.prog6.warehouse.adapters.in.web;

import be.kdg.prog6.warehouse.adapters.in.web.dtos.WarehouseActivityDto;
import be.kdg.prog6.warehouse.adapters.in.web.dtos.WarehouseDto;
import be.kdg.prog6.warehouse.ports.in.GetWarehouseUseCase;
import be.kdg.prog6.warehouse.ports.in.WarehouseCurrentAmountCommand;
import be.kdg.prog6.warehouse.ports.in.WarehouseCurrentAmountQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {


    private final GetWarehouseUseCase getWarehouseUseCase;
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);
    private final WarehouseCurrentAmountQuery warehouseCurrentAmountQuery;


    public WarehouseController(GetWarehouseUseCase getWarehouseUseCase, WarehouseCurrentAmountQuery warehouseCurrentAmountQuery) {
        this.getWarehouseUseCase = getWarehouseUseCase;
        this.warehouseCurrentAmountQuery = warehouseCurrentAmountQuery;
    }

    @GetMapping("/{warehouseId}")
    @PreAuthorize("hasAuthority('warehouse_manager')")
    public ResponseEntity<WarehouseDto> getWarehouse(@AuthenticationPrincipal Jwt token,
                                                     @PathVariable UUID warehouseId
    ) {
        LOGGER.info("WarehouseController is running getWarehouse with id {}", warehouseId);
        var warehouse = getWarehouseUseCase.getWarehouse(warehouseId);
        LOGGER.info("WarehouseController is returning {}", warehouse);
        return new ResponseEntity<>(new WarehouseDto(
                warehouse.getId(),
                warehouse.getSeller().getName(),
                warehouse.getMaterial().toString(),
                warehouse.getWarehouseActivities()
                        .stream()
                        .map(wha -> new WarehouseActivityDto(wha.action(), wha.amountTons(), wha.activityDate()))
                        .toList()
        ), HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}/contents")
    public double getAmountTons(@PathVariable UUID warehouseId) {
        return warehouseCurrentAmountQuery.getCurrentAmountQuery(new WarehouseCurrentAmountCommand(warehouseId));
    }



}
