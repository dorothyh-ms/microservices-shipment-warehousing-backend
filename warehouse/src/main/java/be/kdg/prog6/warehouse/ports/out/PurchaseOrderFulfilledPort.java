package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;

public interface PurchaseOrderFulfilledPort {
    public void purchaseOrderFulfilled(PurchaseOrder po);
}
