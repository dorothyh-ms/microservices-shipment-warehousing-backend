package be.kdg.prog6.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record WeighbridgeDepartureEvent(UUID materialID,UUID warehouseId, LocalDateTime deliveryTime, double amount, WarehouseActivityType type) {

    @Override
    public String toString() {
        return "WeighbridgeDepartureEvent{" +
                "materialID=" + materialID +
                ", warehouseId=" + warehouseId +
                ", deliveryTime=" + deliveryTime +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}
