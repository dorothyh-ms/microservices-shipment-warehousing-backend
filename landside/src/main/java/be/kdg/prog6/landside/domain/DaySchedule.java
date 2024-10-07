package be.kdg.prog6.landside.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DaySchedule {

    private LocalDate date;
    private List<AppointmentWindow> appointmentWindowList = new ArrayList<>();

    public DaySchedule(LocalDate date) {
        this.date = date;
        this.appointmentWindowList = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Optional<Appointment> bookAppointment( UUID materialUUID, String truckLicensePlate, double amountTons, LocalDateTime slotTime, Warehouse warehouse) {
        Optional<AppointmentWindow> hourWindow = appointmentWindowList.stream().filter(hour -> hour.getTimeSlot().getHour() == slotTime.getHour()).findFirst();
        if (hourWindow.isPresent()){
            return hourWindow.get().addAppointment( materialUUID, truckLicensePlate, slotTime, warehouse, amountTons);
        }
            AppointmentWindow newAppointmentWindow = new AppointmentWindow(slotTime);
            Optional<Appointment> appointment = newAppointmentWindow.addAppointment( materialUUID, truckLicensePlate, slotTime, warehouse, amountTons);
            appointmentWindowList.add(newAppointmentWindow);
            return appointment;
    };

}
