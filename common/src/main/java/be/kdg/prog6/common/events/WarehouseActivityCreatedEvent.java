package be.kdg.prog6.common.events;

import java.util.UUID;

public record WarehouseActivityCreatedEvent(UUID warehouseId, WarehouseActivityType action, double amountTons) {
}
