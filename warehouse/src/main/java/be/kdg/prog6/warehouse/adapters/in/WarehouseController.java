package be.kdg.prog6.warehouse.adapters.in;

import be.kdg.prog6.warehouse.adapters.in.dtos.WarehouseDto;
import be.kdg.prog6.warehouse.ports.in.GetActivitiesOfWarehouseUseCase;
import be.kdg.prog6.warehouse.ports.in.GetWarehousesUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final GetWarehousesUseCase getWarehousesUseCase;



    public WarehouseController(GetWarehousesUseCase getWarehousesUseCase) {
        this.getWarehousesUseCase = getWarehousesUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('warehouse_manager')")
    public ResponseEntity<List<WarehouseDto>> getWarehouses(@AuthenticationPrincipal Jwt token) {
        var warehouses = getWarehousesUseCase.getWarehouses();
        return new ResponseEntity<>(
                warehouses.stream().map(
                        warehouse -> new WarehouseDto(
                                warehouse.getSeller().getName(),
                                warehouse.getMaterial().getName(),
                                0
                        )
                ).toList(),
                HttpStatus.OK
        );
    }

//    @GetMapping("/{warehouseId}/activities")
//    @PreAuthorize("hasAuthority('warehouse_manager')")
//    public void getActivitiesOfWarehouse(@AuthenticationPrincipal Jwt token) {
//
//
//    }
}
