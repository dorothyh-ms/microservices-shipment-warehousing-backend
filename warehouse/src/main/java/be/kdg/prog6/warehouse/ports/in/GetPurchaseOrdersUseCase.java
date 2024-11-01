package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.domain.PurchaseOrderStatus;

import java.util.List;

public interface GetPurchaseOrdersUseCase {

    public List<PurchaseOrder> getPurchaseOrders(String status, String sellerName);
}
