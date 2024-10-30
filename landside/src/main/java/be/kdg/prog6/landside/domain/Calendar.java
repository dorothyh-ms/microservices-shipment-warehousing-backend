package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.Material;

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


    public Optional<Appointment> bookDeliveryAppointment(Material material, String truckLicensePlate, double amountTons, LocalDateTime slotTime, Warehouse warehouse) {
        Optional<DaySchedule> daySchedule = daySchedules.stream().filter(day -> day.getDate().getDayOfYear() == slotTime.getDayOfYear()).findFirst();
        if (daySchedule.isPresent()){
            return daySchedule.get().bookAppointment(material, truckLicensePlate, amountTons, slotTime, warehouse);
        }
        DaySchedule newDaySchedule = new DaySchedule(slotTime.toLocalDate());
        daySchedules.add(newDaySchedule);
        return newDaySchedule.bookAppointment( material, truckLicensePlate, amountTons, slotTime, warehouse);
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "daySchedules=" + daySchedules +
                '}';
    }
}
