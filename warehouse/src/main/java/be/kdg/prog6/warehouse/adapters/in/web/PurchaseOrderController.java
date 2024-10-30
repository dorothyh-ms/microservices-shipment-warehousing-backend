package be.kdg.prog6.warehouse.adapters.in.web;


import be.kdg.prog6.warehouse.adapters.in.web.dtos.PurchaseOrderDto;
import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.ports.in.GetPurchaseOrdersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final GetPurchaseOrdersUseCase purchaseOrdersUseCase;

    public PurchaseOrderController(GetPurchaseOrdersUseCase purchaseOrdersUseCase) {
        this.purchaseOrdersUseCase = purchaseOrdersUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('warehouse_manager')")
    public ResponseEntity<List<PurchaseOrderDto>> getPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrdersUseCase.getPurchaseOrders();
        return new ResponseEntity<>(purchaseOrders.stream().map(
                po -> new PurchaseOrderDto(
                        po.getId(),
                        po.getPurchaseOrderNumber(),
                        po.getBuyerEnterpriseNumber(),
                        po.getSeller().getId(),
                        po.getSeller().getName(),
                        po.getOrderLines(),
                        po.getOrderDateTime(),
                        po.getStatus()
                        )
        ).toList(), HttpStatus.OK);
    }
}
