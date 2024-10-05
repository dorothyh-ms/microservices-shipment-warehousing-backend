package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.common.events.WarehouseActivityType;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddMaterialToWarehouseCommand(UUID materialID, UUID sellerId, UUID warehouseId, LocalDateTime deliveryTime, double amountTons) {
}
