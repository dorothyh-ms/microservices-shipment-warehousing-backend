package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.common.domain.Material;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddMaterialToWarehouseCommand(Material material, UUID sellerId, UUID warehouseId, LocalDateTime deliveryTime, double amountTons) {
}
