package be.kdg.prog6.landside.domain;

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

    public Optional<Appointment> addAppointment( UUID materialUUID, String truckLicensePlate, LocalDateTime slotTime, Warehouse warehouse){
        if (appointments.size() == MAX_APPOINTMENT_NUMBER){
            throw new AppointmentLimitExceededException("No more than 40 appointments are allowed per hour");
        }
        Appointment appointment = new Appointment( materialUUID, truckLicensePlate, slotTime, warehouse);
        appointments.add(appointment);
        return Optional.of(appointment);
    }

    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }
}
