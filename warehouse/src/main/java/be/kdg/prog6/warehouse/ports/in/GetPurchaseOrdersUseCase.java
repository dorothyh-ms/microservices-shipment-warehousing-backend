package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;

import java.util.List;

public interface GetPurchaseOrdersUseCase {

    public List<PurchaseOrder> getPurchaseOrders();
}
