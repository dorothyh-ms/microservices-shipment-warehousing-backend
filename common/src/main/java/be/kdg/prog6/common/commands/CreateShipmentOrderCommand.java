package be.kdg.prog6.common.commands;

import java.util.UUID;

public record CreateShipmentOrderCommand(String purchaseOrderNo, String shippingOrderNo, String vesselNumber) {

    @Override
    public String toString() {
        return "CreateShipmentOrderCommand{" +
                "purchaseOrderNo='" + purchaseOrderNo + '\'' +
                ", shippingOrderNo='" + shippingOrderNo + '\'' +
                ", vesselNumber='" + vesselNumber + '\'' +
                '}';
    }
}
