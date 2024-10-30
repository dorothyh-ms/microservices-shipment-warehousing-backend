package be.kdg.prog6.waterside.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class BunkeringOperation{

    private UUID id;
    private ShippingOrder shippingOrder;
    private LocalDateTime dateCompleted;
    private LocalDate datePlanned;

    public BunkeringOperation(UUID id, ShippingOrder shippingOrder) {
        this.id = id;
        this.shippingOrder = shippingOrder;
    }

    public BunkeringOperation(UUID id, ShippingOrder shippingOrder, LocalDateTime dateCompleted, LocalDate datePlanned) {
        this.id = id;
        this.shippingOrder = shippingOrder;
        this.dateCompleted = dateCompleted;
        this.datePlanned = datePlanned;
    }

    public UUID getId() {
        return id;
    }

    public ShippingOrder getShippingOrder() {
        return shippingOrder;
    }

    public LocalDateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public LocalDate getDatePlanned() {
        return datePlanned;
    }

    public void setDatePlanned(LocalDate datePlanned) {
        this.datePlanned = datePlanned;
    }
}
