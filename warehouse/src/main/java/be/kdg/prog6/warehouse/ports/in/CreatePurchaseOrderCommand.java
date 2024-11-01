package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.common.domain.PurchaseOrderLine;

import java.util.List;
import java.util.UUID;

public record CreatePurchaseOrderCommand(String purchaseOrderNumber, UUID sellerId, String buyerEnterpriseNumber, String vesselNumber, List<PurchaseOrderLine> orderLines) {

    @Override
    public String toString() {
        return "CreatePurchaseOrderCommand{" +
                "purchaseOrderNumber='" + purchaseOrderNumber + '\'' +
                ", sellerId=" + sellerId +
                ", buyerEnterpriseNumber='" + buyerEnterpriseNumber + '\'' +
                ", vesselNumber='" + vesselNumber + '\'' +
                ", orderLines=" + orderLines +
                '}';
    }
}
