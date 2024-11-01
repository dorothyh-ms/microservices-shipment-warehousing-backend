package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.adapters.out.db.SpringDataAppointmentRepository;
import be.kdg.prog6.landside.adapters.out.db.SpringDataLandsideSellerRepository;
import be.kdg.prog6.landside.adapters.out.db.SpringDataWarehouseProjectionRepository;
import be.kdg.prog6.landside.adapters.out.db.entities.DeliveryAppointmentJPAEntity;
import be.kdg.prog6.landside.adapters.out.db.entities.SellerJpa;
import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
import be.kdg.prog6.landside.ports.in.CreateDeliveryAppointmentUseCase;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static be.kdg.prog6.landside.core.TestValues.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DefaultCreateAppointmentUseCaseIntegrationTest extends AbstractDatabaseTest{

    @Autowired
    private CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase;

    @Autowired
    private SpringDataAppointmentRepository appointmentRepository;

    @Autowired
    private SpringDataWarehouseProjectionRepository warehouseProjectionRepository;

    @Autowired
    private SpringDataLandsideSellerRepository sellerRepository;



    @Test
    void shouldCreateAppointment() {

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


        // Act
        createDeliveryAppointmentUseCase.createAppointment(new CreateDeliveryAppointmentCommand(SELLER_NAME, MATERIAL, LICENSE_PLATE, AMOUNT_TONS, APPOINTMENT_TIME));


        // Assert
        final Optional<DeliveryAppointmentJPAEntity> appointmentJPAEntityOptional = appointmentRepository.findFirstByTruckLicensePlateAndDateAndHour(LICENSE_PLATE, APPOINTMENT_TIME.toLocalDate(), APPOINTMENT_TIME.getHour() );
        assertEquals(appointmentJPAEntityOptional.isPresent(), Boolean.TRUE);

    }

}
