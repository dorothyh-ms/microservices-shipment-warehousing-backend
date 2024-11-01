package be.kdg.prog6.landside.adapters.in.web.dtos;

import java.time.LocalDate;
import java.util.List;

public class UnavailableDateDto {
    private LocalDate date;

    public UnavailableDateDto(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
