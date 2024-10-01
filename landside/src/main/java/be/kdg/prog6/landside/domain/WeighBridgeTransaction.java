package be.kdg.prog6.landside.domain;

import java.util.UUID;

public class WeighBridgeTransaction {

    private UUID id;

    private String truckLicensePlate;
    private WeighbridgeMeasurement weightIn;

    private WeighbridgeMeasurement weightOut;

    private Appointment appointment;

    public WeighBridgeTransaction(Appointment appointment, WeighbridgeMeasurement weightIn) {
        this.appointment = appointment;
        this.weightIn = weightIn;
    }

    public WeighBridgeTransaction(UUID id, Appointment appointment, WeighbridgeMeasurement weightIn, WeighbridgeMeasurement weightOut) {
        this.id = id;
        this.appointment = appointment;
        this.weightIn = weightIn;
        this.weightOut = weightOut;
    }

    public String getTruckLicensePlate() {
        return truckLicensePlate;
    }

    public void setTruckLicensePlate(String truckLicensePlate) {
        this.truckLicensePlate = truckLicensePlate;
    }

    public WeighbridgeMeasurement getWeightIn() {
        return weightIn;
    }

    public void setWeightIn(WeighbridgeMeasurement weightIn) {
        this.weightIn = weightIn;
    }

    public WeighbridgeMeasurement getWeightOut() {
        return weightOut;
    }

    public void setWeightOut(WeighbridgeMeasurement weightOut) {
        this.weightOut = weightOut;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
