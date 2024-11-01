package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.adapters.out.db.SpringDataAppointmentRepository;
import be.kdg.prog6.landside.adapters.out.db.SpringDataLandsideSellerRepository;
import be.kdg.prog6.landside.adapters.out.db.SpringDataWarehouseProjectionRepository;
import be.kdg.prog6.landside.adapters.out.db.entities.DeliveryAppointmentJPAEntity;
import be.kdg.prog6.landside.adapters.out.db.entities.SellerJpa;
import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import be.kdg.prog6.landside.ports.in.CreateDeliveryAppointmentUseCase;
import be.kdg.prog6.landside.ports.in.GateArrivalUseCase;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import be.kdg.prog6.landside.ports.in.commands.ScanLicensePlateCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static be.kdg.prog6.landside.core.TestValues.*;
import static be.kdg.prog6.landside.core.TestValues.APPOINTMENT_TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DefaultGateArrivalUseCaseIntegrationTest extends AbstractDatabaseTest{

    @Autowired
    private GateArrivalUseCase gateArrivalUseCase;

    @Autowired
    private SpringDataAppointmentRepository appointmentRepository;

    @Autowired
    private SpringDataWarehouseProjectionRepository warehouseProjectionRepository;

    @Autowired
    private SpringDataLandsideSellerRepository sellerRepository;



    @Test
    void shouldSetAppointmentInProgress() {

        // Arrange
        SellerJpa seller = new SellerJpa(SELLER_ID, SELLER_NAME);
        seller =sellerRepository.save(seller);
        WarehouseJPAEntity warehouseJPAEntity = new WarehouseJPAEntity(
                WAREHOUSE.getId(),
                seller,
                MATERIAL,
                0,
                0,
                0
        );

        warehouseJPAEntity.setSeller(seller);

        warehouseProjectionRepository.save(warehouseJPAEntity);

        DeliveryAppointmentJPAEntity deliveryAppointmentJPA = new DeliveryAppointmentJPAEntity(
                UUID.randomUUID(),
                MATERIAL,
                LICENSE_PLATE,
                LocalDate.now(),
                LocalDateTime.now().getHour(),
                AppointmentStatus.CREATED,
                warehouseJPAEntity,
                AMOUNT_TONS);

        deliveryAppointmentJPA = appointmentRepository.save(deliveryAppointmentJPA);

        // Act
        gateArrivalUseCase.checkTruck(new ScanLicensePlateCommand( LICENSE_PLATE));

        // Assert
        final Optional<DeliveryAppointmentJPAEntity> appointmentJPAEntityOptional = appointmentRepository.findFirstByTruckLicensePlateAndDateAndHour(LICENSE_PLATE, APPOINTMENT_TIME.toLocalDate(), APPOINTMENT_TIME.getHour() );
        assertEquals(appointmentJPAEntityOptional.isPresent(), Boolean.TRUE);
        assertEquals(appointmentJPAEntityOptional.get().getStatus(), AppointmentStatus.IN_PROGRESS);

    }
}
