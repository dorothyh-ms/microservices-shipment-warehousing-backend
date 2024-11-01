package be.kdg.prog6.landside.core;


import be.kdg.prog6.landside.adapters.in.web.AppointmentController;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.Calendar;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.exceptions.NoWarehouseAvailableException;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import be.kdg.prog6.landside.ports.in.CreateDeliveryAppointmentUseCase;
import be.kdg.prog6.landside.ports.out.AppointmentBookedPort;

import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCreateDeliveryAppointmentUseCase implements CreateDeliveryAppointmentUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCreateDeliveryAppointmentUseCase.class);
    private final AppointmentBookedPort appointmentBookedPort;
    private final WarehouseLoadPort warehouseLoadPort;

    public DefaultCreateDeliveryAppointmentUseCase(AppointmentBookedPort appointmentBookedPort, WarehouseLoadPort warehouseLoadPort) {
        this.appointmentBookedPort = appointmentBookedPort;
        this.warehouseLoadPort = warehouseLoadPort;
    }

    @Override
    @Transactional
    public Appointment createAppointment(CreateDeliveryAppointmentCommand createDeliveryAppointmentCommand) {
        LOGGER.info("DefaultCreateDeliveryAppointmentUseCase is running createAppointment with command {}", createDeliveryAppointmentCommand);
        // Check if a warehouse is available
        List<Warehouse> warehouses = warehouseLoadPort.loadWarehousesBySellerName(createDeliveryAppointmentCommand.sellerName());
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
            Appointment appointment = createdAppointment.get();
            appointmentBookedPort.appointmentBooked(appointment);
            return appointment;
        }
        return null;

    }
}
