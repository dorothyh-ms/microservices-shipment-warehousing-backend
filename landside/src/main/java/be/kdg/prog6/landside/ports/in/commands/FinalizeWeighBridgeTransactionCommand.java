package be.kdg.prog6.landside.ports.in.commands;

public record FinalizeWeighBridgeTransactionCommand(String truckLicensePlate, double amountTons) {
}
