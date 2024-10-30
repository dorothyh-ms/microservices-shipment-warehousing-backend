package be.kdg.prog6.waterside.adapters.in.web.dtos;

import java.time.LocalDate;

public class PlanBunkeringOperationDto {
    private LocalDate plannedDate;

    public PlanBunkeringOperationDto(LocalDate plannedDate) {
        this.plannedDate = plannedDate;
    }

    public PlanBunkeringOperationDto() {
    }

    public LocalDate getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(LocalDate plannedDate) {
        this.plannedDate = plannedDate;
    }

    @Override
    public String toString() {
        return "PlanBunkeringOperationDto{" +
                "plannedDate=" + plannedDate +
                '}';
    }
}
