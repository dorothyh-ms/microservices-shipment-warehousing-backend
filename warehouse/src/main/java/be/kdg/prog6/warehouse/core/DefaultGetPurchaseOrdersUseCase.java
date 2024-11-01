package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;
import be.kdg.prog6.warehouse.domain.PurchaseOrderStatus;
import be.kdg.prog6.warehouse.exceptions.InvalidPurchaseOrderStatusException;
import be.kdg.prog6.warehouse.ports.in.GetPurchaseOrdersUseCase;
import be.kdg.prog6.warehouse.ports.out.LoadPurchaseOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetPurchaseOrdersUseCase implements GetPurchaseOrdersUseCase {
    private final LoadPurchaseOrderPort purhcaseOrderLoadPort;

    public DefaultGetPurchaseOrdersUseCase(LoadPurchaseOrderPort purhcaseOrderLoadPort) {
        this.purhcaseOrderLoadPort = purhcaseOrderLoadPort;
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrders(String status, String sellerName) {
        PurchaseOrderStatus poStatus = null;
        if (status != null) {
            if (!PurchaseOrderStatus.isValid(status.toUpperCase())) {
                throw new InvalidPurchaseOrderStatusException("Passed status was invalid");
            }
            else {
                poStatus = PurchaseOrderStatus.valueOf(status.toUpperCase());
            }
        }
        return purhcaseOrderLoadPort.loadPurchaseOrders(poStatus, sellerName);
    }
}
