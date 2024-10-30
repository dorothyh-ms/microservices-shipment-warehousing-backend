package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.landside.exceptions.AppointmentLimitExceededException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AppointmentWindow {


    private final LocalDateTime timeSlot;
    private static final int MAX_APPOINTMENT_NUMBER = 40;

    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentWindow(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Optional<Appointment> addAppointment(Material material, String truckLicensePlate, LocalDateTime slotTime, Warehouse warehouse, double amountTons){
        if (appointments.size() == MAX_APPOINTMENT_NUMBER){
            throw new AppointmentLimitExceededException("No more than 40 appointments are allowed per hour");
        }
        Appointment appointment = new Appointment( material, truckLicensePlate, slotTime, warehouse, amountTons);

        appointments.add(appointment);
        return Optional.of(appointment);
    }

    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return "AppointmentWindow{" +
                "timeSlot=" + timeSlot +
                ", appointments=" + appointments +
                '}';
    }
}
