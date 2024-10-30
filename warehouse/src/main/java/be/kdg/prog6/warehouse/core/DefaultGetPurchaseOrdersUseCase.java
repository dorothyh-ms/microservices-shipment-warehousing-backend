package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.PurchaseOrder;
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
    public List<PurchaseOrder> getPurchaseOrders() {
        return purhcaseOrderLoadPort.loadPurchaseOrders();
    }
}
