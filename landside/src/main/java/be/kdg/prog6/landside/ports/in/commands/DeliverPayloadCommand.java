package be.kdg.prog6.landside.ports.in.commands;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeliverPayloadCommand(String truckLicensePlate, double amountTons) {

}
