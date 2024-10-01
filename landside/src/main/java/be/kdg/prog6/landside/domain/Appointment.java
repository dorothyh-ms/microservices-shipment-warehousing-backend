package be.kdg.prog6.landside.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    private UUID id;

    private UUID materialUUID;

    private String truckLicensePlate;

    private double amountTons;

    private LocalDateTime timeSlotStart;

    private AppointmentStatus status;

    private Warehouse warehouse;



    public Appointment() {
    }

    public Appointment(UUID materialUUID, String truckLicensePlate, LocalDateTime timeSlotStart, Warehouse warehouse) {
        this.materialUUID = materialUUID;
        this.truckLicensePlate = truckLicensePlate;
        this.timeSlotStart = timeSlotStart;
        this.status = AppointmentStatus.CREATED;
        this.warehouse = warehouse;
    }

    public Appointment(UUID id, UUID materialUUID, String truckLicensePlate, LocalDateTime timeSlotStart, AppointmentStatus status, Warehouse warehouse) {
        this.id = id;
        this.materialUUID = materialUUID;
        this.truckLicensePlate = truckLicensePlate;
        this.timeSlotStart = timeSlotStart;
        this.status = status;
        this.warehouse = warehouse;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public UUID getMaterialUUID() {
        return materialUUID;
    }

    public void setMaterialUUID(UUID materialUUID) {
        this.materialUUID = materialUUID;
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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void consolidate() {
        this.status = AppointmentStatus.CONSOLIDATED;
    }

    public void inProgress(){
        this.status = AppointmentStatus.IN_PROGRESS;
    }

}
