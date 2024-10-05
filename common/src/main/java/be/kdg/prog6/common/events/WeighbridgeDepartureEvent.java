package be.kdg.prog6.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record WeighbridgeDepartureEvent(UUID materialID, UUID sellerId, UUID warehouseId, LocalDateTime deliveryTime, double amount) {

    @Override
    public String toString() {
        return "WeighbridgeDepartureEvent{" +
                "materialID=" + materialID +
                ", sellerId=" + sellerId +
                ", warehouseId=" + warehouseId +
                ", deliveryTime=" + deliveryTime +
                ", amount=" + amount +
                '}';
    }
}
