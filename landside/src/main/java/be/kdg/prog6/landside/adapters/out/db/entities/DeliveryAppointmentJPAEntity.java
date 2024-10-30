package be.kdg.prog6.landside.adapters.out.db.entities;


import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(catalog="landside", name="appointments")
public class DeliveryAppointmentJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="seller_id")
    private UUID sellerUUID;

    @Enumerated(EnumType.STRING)
    private Material material;


    @Column(name="truck_license_plate")
    private String truckLicensePlate;


    @Column(name="appointment_date")
    private LocalDate date;


    @Column(name="appointment_hour")
    private int hour;


    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name="warehouse_id", nullable=false)
    private WarehouseJPAEntity assignedWarehouse;


    @Column(name="amount_tons")
    private double amountTons;

    public DeliveryAppointmentJPAEntity() {
    }

    public double getAmountTons() {
        return amountTons;
    }

    public void setAmountTons(double amountTons) {
        this.amountTons = amountTons;
    }

    public DeliveryAppointmentJPAEntity(Material material, String truckLicensePlate, LocalDate date, int hour, AppointmentStatus status, WarehouseJPAEntity assignedWarehouse, double amountTons) {
        this.material = material;
        this.truckLicensePlate = truckLicensePlate;
        this.date = date;
        this.hour = hour;
        this.status = status;
        this.assignedWarehouse= assignedWarehouse;
        this.amountTons = amountTons;
    }

    public DeliveryAppointmentJPAEntity(UUID id, Material material, String truckLicensePlate, LocalDate date, int hour, AppointmentStatus status, WarehouseJPAEntity assignedWarehouse) {
        this.id = id;
        this.material =material;
        this.truckLicensePlate = truckLicensePlate;
        this.date = date;
        this.hour = hour;
        this.status = status;
        this.assignedWarehouse= assignedWarehouse;
    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
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


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }


    public WarehouseJPAEntity getAssignedWarehouse() {
        return assignedWarehouse;
    }

    public void setAssignedWarehouse(WarehouseJPAEntity assignedWarehouse) {
        this.assignedWarehouse = assignedWarehouse;
    }
}
