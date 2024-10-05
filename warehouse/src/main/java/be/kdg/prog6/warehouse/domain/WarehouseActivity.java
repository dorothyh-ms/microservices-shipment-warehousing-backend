package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.events.WarehouseActivityType;

import java.time.LocalDateTime;

public record WarehouseActivity(WarehouseActivityType action, double amountTons, LocalDateTime activityDate) {


//    public WarehouseActivity {
//        Objects.requireNonNull(id);
//        Objects.requireNonNull(type);
//        if (amount <= 0) {
//            throw new InvalidOperationException("Amount must always be larger than 0");
//        }
//    }


    @Override
    public String toString() {
        return "WarehouseActivity{" +
                "action=" + action +
                ", amountTons=" + amountTons +
                ", activityDate=" + activityDate +
                '}';
    }
}
