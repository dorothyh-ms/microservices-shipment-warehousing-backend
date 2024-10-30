package be.kdg.prog6.common.events;

import be.kdg.prog6.common.domain.Material;

import java.time.LocalDateTime;
import java.util.UUID;

public record WeighbridgeDepartureEvent(Material material, UUID sellerId, UUID warehouseId, LocalDateTime deliveryTime, double amount) {

    @Override
    public String toString() {
        return "WeighbridgeDepartureEvent{" +
                "material=" + material +
                ", sellerId=" + sellerId +
                ", warehouseId=" + warehouseId +
                ", deliveryTime=" + deliveryTime +
                ", amount=" + amount +
                '}';
    }
}
