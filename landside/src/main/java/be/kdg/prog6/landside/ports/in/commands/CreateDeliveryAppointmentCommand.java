package be.kdg.prog6.landside.ports.in.commands;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateDeliveryAppointmentCommand(UUID sellerUUID, UUID materialUUID, String truckLicensePlate, double amountTons, LocalDateTime slotStartTime ) {
}
