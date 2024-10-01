package be.kdg.prog6.landside.domain;

import java.time.LocalDateTime;

public record WeighbridgeMeasurement(double weightTons, LocalDateTime weighDateTime) {
}
