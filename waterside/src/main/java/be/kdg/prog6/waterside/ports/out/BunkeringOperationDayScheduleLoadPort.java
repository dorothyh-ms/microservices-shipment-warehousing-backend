package be.kdg.prog6.waterside.ports.out;

import be.kdg.prog6.waterside.domain.BunkeringOperationDaySchedule;

import java.time.LocalDate;

public interface BunkeringOperationDayScheduleLoadPort {

    public BunkeringOperationDaySchedule loadBunkeringOperationDaySchedule(LocalDate date);
}
