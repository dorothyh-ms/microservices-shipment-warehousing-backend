package be.kdg.prog6.landside.ports.in.commands;

public record CreateWeighBridgeTransactionCommand(String truckLicensePlate, double amountTons) {
}
