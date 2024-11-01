package be.kdg.prog6.landside.adapters.in.web.dtos;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import be.kdg.prog6.landside.domain.Warehouse;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppointmentDto {


    private UUID id;

    private Material material;

    private String truckLicensePlate;

    private double amountTons;

    private LocalDateTime timeSlotStart;

    private AppointmentStatus status;


    public AppointmentDto() {
    }

    public AppointmentDto(UUID id, Material material, String truckLicensePlate, double amountTons, LocalDateTime timeSlotStart, AppointmentStatus status) {
        this.id = id;
        this.material = material;
        this.truckLicensePlate = truckLicensePlate;
        this.amountTons = amountTons;
        this.timeSlotStart = timeSlotStart;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getTruckLicensePlate() {
        return truckLicensePlate;
    }

    public void setTruckLicensePlate(String truckLicensePlate) {
        this.truckLicensePlate = truckLicensePlate;
    }

    public double getAmountTons() {
        return amountTons;
    }

    public void setAmountTons(double amountTons) {
        this.amountTons = amountTons;
    }

    public LocalDateTime getTimeSlotStart() {
        return timeSlotStart;
    }

    public void setTimeSlotStart(LocalDateTime timeSlotStart) {
        this.timeSlotStart = timeSlotStart;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
