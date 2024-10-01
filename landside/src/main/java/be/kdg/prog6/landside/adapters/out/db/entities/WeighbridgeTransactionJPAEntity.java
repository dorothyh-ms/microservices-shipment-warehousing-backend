package be.kdg.prog6.landside.adapters.out.db.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog="landside", name="weighbridge_transactions")
public class WeighbridgeTransactionJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="warehouse_id")
    private UUID id;


    @OneToOne
    @JoinColumn(name="appointment_id", nullable=false)
    private DeliveryAppointmentJPAEntity deliveryAppointment;


    @Column(name="tons_in")
    private double arrivalWeightTons;

    @Column(name="tons_out", nullable = true)
    private double departureWeightTons;


    @Column(name="timestamp_in", nullable = false)
    private LocalDateTime arrivalDateTime;

    @Column(name="timestamp_out", nullable = true)
    private LocalDateTime departureDateTime;

    public WeighbridgeTransactionJPAEntity() {
    }

    public WeighbridgeTransactionJPAEntity(UUID id, DeliveryAppointmentJPAEntity deliveryAppointment, double arrivalWeightTons, double departureWeightTons, LocalDateTime arrivalDateTime) {
        this.id = id;
        this.deliveryAppointment = deliveryAppointment;
        this.arrivalWeightTons = arrivalWeightTons;
        this.departureWeightTons = departureWeightTons;
        this.arrivalDateTime = arrivalDateTime;
    }

    public WeighbridgeTransactionJPAEntity(UUID id, DeliveryAppointmentJPAEntity deliveryAppointment, double arrivalWeightTons, double departureWeightTons, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime) {
        this.id = id;
        this.deliveryAppointment = deliveryAppointment;
        this.arrivalWeightTons = arrivalWeightTons;
        this.departureWeightTons = departureWeightTons;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DeliveryAppointmentJPAEntity getDeliveryAppointment() {
        return deliveryAppointment;
    }

    public void setDeliveryAppointment(DeliveryAppointmentJPAEntity deliveryAppointment) {
        this.deliveryAppointment = deliveryAppointment;
    }

    public double getArrivalWeightTons() {
        return arrivalWeightTons;
    }

    public void setArrivalWeightTons(double arrivalWeightTons) {
        this.arrivalWeightTons = arrivalWeightTons;
    }

    public double getDepartureWeightTons() {
        return departureWeightTons;
    }

    public void setDepartureWeightTons(double departureWeightTons) {
        this.departureWeightTons = departureWeightTons;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
}
