package be.kdg.prog6.common.commands;

import be.kdg.prog6.common.domain.PurchaseOrderLine;

import java.util.List;
import java.util.UUID;

public record RemoveMaterialForShipmentCommand(String purchaseOrderNumber) {
}
