package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.Material;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    private UUID id;

    private Material material;

    private String truckLicensePlate;

    private double amountTons;

    private LocalDateTime timeSlotStart;

    private AppointmentStatus status;

    private Warehouse warehouse;



    public Appointment() {
    }

    public Appointment(String truckLicensePlate) {
        this.truckLicensePlate = truckLicensePlate;
    }

    public Appointment(Material material, String truckLicensePlate, LocalDateTime timeSlotStart, Warehouse warehouse, double amountTons) {
        this.material = material;
        this.truckLicensePlate = truckLicensePlate;
        this.timeSlotStart = timeSlotStart;
        this.status = AppointmentStatus.CREATED;
        this.warehouse = warehouse;
        this.amountTons = amountTons;
    }

    public Appointment(UUID id, Material material, String truckLicensePlate, LocalDateTime timeSlotStart, AppointmentStatus status, Warehouse warehouse) {
        this.id = id;
        this.material= material;
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


    public Material getMaterial() {
        return material;
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

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", material=" + material +
                ", truckLicensePlate='" + truckLicensePlate + '\'' +
                ", amountTons=" + amountTons +
                ", timeSlotStart=" + timeSlotStart +
                ", status=" + status +
                ", warehouse=" + warehouse +
                '}';
    }
}
