package be.kdg.prog6.landside.ports.in;

import java.time.LocalDate;
import java.util.List;

public interface GetUnavailableDatesUseCase {
    public List<LocalDate> getInvalidDatesForAppointment();
}
