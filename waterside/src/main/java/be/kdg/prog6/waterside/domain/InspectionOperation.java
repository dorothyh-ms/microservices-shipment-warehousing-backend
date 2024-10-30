package be.kdg.prog6.waterside.domain;

import be.kdg.prog6.waterside.ports.out.ShippingOrderCreatePort;

import java.time.LocalDateTime;
import java.util.UUID;

public class InspectionOperation {

    private UUID id;
    private String signature;
    private LocalDateTime dateInspected;
    private ShippingOrder shippingOrder;


    public InspectionOperation(UUID id, String signature, LocalDateTime dateInspected, ShippingOrder shippingOrder) {
        this.id = id;
        this.signature = signature;
        this.dateInspected = dateInspected;
        this.shippingOrder = shippingOrder;
    }

    public InspectionOperation(UUID id,ShippingOrder shippingOrder) {
        this.id = id;
        this.shippingOrder = shippingOrder;
    }

    public UUID getId() {
        return id;
    }

    public String getSignature() {
        return signature;
    }

    public LocalDateTime getDateInspected() {
        return dateInspected;
    }

    public ShippingOrder getShippingOrder() {
        return shippingOrder;
    }

    public void setDateInspected(LocalDateTime dateInspected) {
        this.dateInspected = dateInspected;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "InspectionOperation{" +
                "id=" + id +
                ", signature='" + signature + '\'' +
                ", dateInspected=" + dateInspected +
                ", shippingOrder=" + shippingOrder +
                '}';
    }
}
