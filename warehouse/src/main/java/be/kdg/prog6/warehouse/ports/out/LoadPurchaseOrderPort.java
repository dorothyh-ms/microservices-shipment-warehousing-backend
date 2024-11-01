package be.kdg.prog6.warehouse.ports.out;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.domain.PurchaseOrderStatus;

import java.util.List;
import java.util.Optional;

public interface LoadPurchaseOrderPort {

    public List<PurchaseOrder> loadPurchaseOrders(PurchaseOrderStatus status, String sellerName);


    public Optional<PurchaseOrder> loadPurchaseOrderByPurchaseOrderNumber(String purchaseOrderNumber);


}
