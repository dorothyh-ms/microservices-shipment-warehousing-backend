package be.kdg.prog6.landside.adapters.in.web.dtos;

import be.kdg.prog6.common.domain.Material;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateAppointmentDto {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeSlot;
    private String truckLicensePlate;
    private double amountTons;

    private String sellerName;
    private Material material;


    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
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


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public String toString() {
        return "CreateAppointmentDto{" +
                "timeSlot=" + timeSlot +
                ", truckLicensePlate='" + truckLicensePlate + '\'' +
                ", amountTons=" + amountTons +
                ", sellerName='" + sellerName + '\'' +
                ", material=" + material +
                '}';
    }
}
