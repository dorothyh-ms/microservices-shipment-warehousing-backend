package be.kdg.prog6.landside.adapters.in.dtos;

import java.time.LocalDateTime;

public class WeighbridgeTransactionDto {
    private String licensePlate;
    private double grossWeightTons;
    private double netWeightTons;
    private LocalDateTime departureWeightDateTime;
}
