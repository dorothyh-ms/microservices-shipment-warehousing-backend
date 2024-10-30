package be.kdg.prog6.waterside.adapters.in.web.dtos;

import be.kdg.prog6.waterside.domain.ShippingOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class BunkeringOperationDto {

    private UUID id;
    private String vesselNumber;
    private LocalDateTime dateCompleted;
    private LocalDate datePlanned;

    public BunkeringOperationDto(UUID id, String vesselNumber, LocalDateTime dateCompleted, LocalDate datePlanned) {
        this.id = id;
        this.vesselNumber = vesselNumber;
        this.dateCompleted = dateCompleted;
        this.datePlanned = datePlanned;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public LocalDateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public LocalDate getDatePlanned() {
        return datePlanned;
    }

    public void setDatePlanned(LocalDate datePlanned) {
        this.datePlanned = datePlanned;
    }
}
