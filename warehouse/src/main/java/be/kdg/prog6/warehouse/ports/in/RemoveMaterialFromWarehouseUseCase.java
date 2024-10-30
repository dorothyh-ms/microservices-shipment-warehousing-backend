package be.kdg.prog6.warehouse.ports.in;

import be.kdg.prog6.common.commands.RemoveMaterialForShipmentCommand;

public interface RemoveMaterialFromWarehouseUseCase {
    public void removeMaterialFromWarehouses(RemoveMaterialForShipmentCommand command);
}
