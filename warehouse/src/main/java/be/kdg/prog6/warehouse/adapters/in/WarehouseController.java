package be.kdg.prog6.warehouse.adapters.in;

import be.kdg.prog6.warehouse.adapters.in.dtos.WarehouseActivityDto;
import be.kdg.prog6.warehouse.adapters.in.dtos.WarehouseDto;
import be.kdg.prog6.warehouse.ports.in.GetActivitiesOfWarehouseUseCase;
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

    @GetMapping("/{warehouseId}/activities")
    @PreAuthorize("hasAuthority('warehouse_manager')")
    public ResponseEntity<List<WarehouseActivityDto>> getActivitiesOfWarehouse(@AuthenticationPrincipal Jwt token) {

        return new ResponseEntity<
                List<WarehouseActivityDto>>(List.of(), HttpStatus.OK);

    }
}
