package be.kdg.prog6.waterside.domain;

import be.kdg.prog6.waterside.exceptions.DailyBunkeringOperationLimitExceededException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BunkeringOperationDaySchedule {

    private LocalDate date;
    private List<BunkeringOperation> bunkeringOperations;
    public static int MAX_BUNKERING_OPERATIONS = 6;

    public void planBunkeringOperation(BunkeringOperation bo) {
        if (bunkeringOperations.size() < MAX_BUNKERING_OPERATIONS) {
            bo.setDatePlanned(date);
            bunkeringOperations.add(bo);
        } else {
            throw new DailyBunkeringOperationLimitExceededException(
                    String.format("There are already 6 bunkering operations planned on {}", this.date.toString())
            );
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public BunkeringOperationDaySchedule(LocalDate date) {
        this.date = date;
        this.bunkeringOperations = new ArrayList<>();
    }
}
