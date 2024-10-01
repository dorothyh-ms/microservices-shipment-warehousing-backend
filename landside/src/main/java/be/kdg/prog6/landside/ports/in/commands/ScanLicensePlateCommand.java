package be.kdg.prog6.landside.ports.in.commands;

import java.time.LocalDateTime;

public class ScanLicensePlateCommand {

    private final String licensePlate;
    private final LocalDateTime arrivalTime;

    public ScanLicensePlateCommand(String licensePlate) {
        this.licensePlate = licensePlate;
        this.arrivalTime = LocalDateTime.now();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
}
