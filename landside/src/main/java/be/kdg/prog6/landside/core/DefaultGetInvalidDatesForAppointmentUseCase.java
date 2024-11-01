package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.ports.in.GetUnavailableDatesUseCase;
import be.kdg.prog6.landside.ports.out.LoadDatesForAppointmentPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DefaultGetInvalidDatesForAppointmentUseCase implements GetUnavailableDatesUseCase {
    private final LoadDatesForAppointmentPort loadDatesForAppointmentPort;

    public DefaultGetInvalidDatesForAppointmentUseCase(LoadDatesForAppointmentPort loadDatesForAppointmentPort) {
        this.loadDatesForAppointmentPort = loadDatesForAppointmentPort;
    }

    public List<LocalDate> getInvalidDatesForAppointment() {
        return loadDatesForAppointmentPort.loadDatesWithAppointmentNumberGreaterThanOrEqualTo(1);
    }
}
