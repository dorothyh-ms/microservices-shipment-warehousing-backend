package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.landside.ports.in.commands.ScanLicensePlateCommand;

public interface GateArrivalUseCase {

    void checkTruck(ScanLicensePlateCommand checkTruckCommand);
}
