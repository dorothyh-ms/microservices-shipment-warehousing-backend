package be.kdg.prog6.landside.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Calendar {
    private List<DaySchedule> daySchedules = new ArrayList<>();

    private static final Calendar INSTANCE = new Calendar();

    public static final Calendar instance(){
        return INSTANCE;
    }


    public Optional<Appointment> bookDeliveryAppointment( UUID materialUUID, String truckLicensePlate, double amountTons, LocalDateTime slotTime, Warehouse warehouse) {
        Optional<DaySchedule> daySchedule = daySchedules.stream().filter(day -> day.getDate().getDayOfYear() == slotTime.getDayOfYear()).findFirst();
        if (daySchedule.isPresent()){
            System.out.println("day schedule is present");
            return daySchedule.get().bookAppointment(materialUUID, truckLicensePlate, amountTons, slotTime, warehouse);
        }
        System.out.println("day schedule is not present");
        DaySchedule newDaySchedule = new DaySchedule(slotTime.toLocalDate());
        daySchedules.add(newDaySchedule);
        return newDaySchedule.bookAppointment( materialUUID, truckLicensePlate, amountTons, slotTime, warehouse);
    }
}
