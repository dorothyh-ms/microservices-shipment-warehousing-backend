package be.kdg.prog6.landside.ports.in.commands;

import be.kdg.prog6.common.domain.Material;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateDeliveryAppointmentCommand(UUID sellerUUID, Material material, String truckLicensePlate, double amountTons, LocalDateTime slotStartTime ) {
}
