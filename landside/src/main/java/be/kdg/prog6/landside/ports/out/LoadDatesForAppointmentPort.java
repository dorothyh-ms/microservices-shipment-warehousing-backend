package be.kdg.prog6.landside.ports.out;

import java.time.LocalDate;
import java.util.List;

public interface LoadDatesForAppointmentPort {
    public List<LocalDate> loadDatesWithAppointmentNumberGreaterThanOrEqualTo(int count);
}
