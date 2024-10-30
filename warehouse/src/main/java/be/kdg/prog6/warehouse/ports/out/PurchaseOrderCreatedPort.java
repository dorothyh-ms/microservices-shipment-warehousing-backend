package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;

public interface PurchaseOrderCreatedPort {
    public void purchaseOrderCreated(PurchaseOrder po);
}
