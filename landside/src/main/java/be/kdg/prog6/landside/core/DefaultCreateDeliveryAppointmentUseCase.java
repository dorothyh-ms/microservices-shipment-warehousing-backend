package be.kdg.prog6.landside.core;


import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.Calendar;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.exceptions.NoWarehouseAvailableException;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import be.kdg.prog6.landside.ports.in.CreateDeliveryAppointmentUseCase;
import be.kdg.prog6.landside.ports.out.AppointmentBookedPort;

import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCreateDeliveryAppointmentUseCase implements CreateDeliveryAppointmentUseCase {

    private final AppointmentBookedPort appointmentBookedPort;
    private final WarehouseLoadPort warehouseLoadPort;

    public DefaultCreateDeliveryAppointmentUseCase(AppointmentBookedPort appointmentBookedPort, WarehouseLoadPort warehouseLoadPort) {
        this.appointmentBookedPort = appointmentBookedPort;
        this.warehouseLoadPort = warehouseLoadPort;
    }

    @Override
    @Transactional
    public boolean createAppointment(CreateDeliveryAppointmentCommand createDeliveryAppointmentCommand) {
        // Check if a warehouse is available
        List<Warehouse> warehouses = warehouseLoadPort.loadWarehousesBySellerId(createDeliveryAppointmentCommand.sellerUUID());
        Optional<Warehouse> assignedWarehouse = warehouses
                .stream()
                .filter(warehouse -> warehouse.isAvailable(createDeliveryAppointmentCommand.material()))
                .findFirst();
        if (assignedWarehouse.isEmpty()){
            throw new NoWarehouseAvailableException("All warehouses are at capacity or cannot store the requested delivery material");
        }

        Warehouse warehouse = assignedWarehouse.get();

        // handle appointment creation
        Optional<Appointment> createdAppointment = Calendar.instance().bookDeliveryAppointment(
                createDeliveryAppointmentCommand.material(),
                createDeliveryAppointmentCommand.truckLicensePlate(),
                createDeliveryAppointmentCommand.amountTons(),
                createDeliveryAppointmentCommand.slotStartTime().truncatedTo(ChronoUnit.HOURS),
                warehouse
                );

        if (createdAppointment.isPresent()){
            appointmentBookedPort.appointmentBooked(createdAppointment.get());
        }
        return createdAppointment.isPresent();

    }
}
