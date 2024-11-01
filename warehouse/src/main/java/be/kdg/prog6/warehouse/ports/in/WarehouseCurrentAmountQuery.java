package be.kdg.prog6.warehouse.ports.in;

public interface WarehouseCurrentAmountQuery {

    public double getCurrentAmountQuery(WarehouseCurrentAmountCommand command);
}
