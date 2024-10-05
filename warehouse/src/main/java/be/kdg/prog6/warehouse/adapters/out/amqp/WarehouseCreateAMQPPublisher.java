package be.kdg.prog6.warehouse.adapters.out.amqp;


import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.ports.out.WarehouseCreatePort;
import org.springframework.stereotype.Component;

@Component
public class WarehouseCreateAMQPPublisher implements WarehouseCreatePort {
    @Override
    public void warehouseCreated(Warehouse warehouse) {

    }
}
