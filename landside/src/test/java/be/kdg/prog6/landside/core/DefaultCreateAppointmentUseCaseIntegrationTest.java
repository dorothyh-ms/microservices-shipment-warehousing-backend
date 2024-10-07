//package be.kdg.prog6.landside.core;
//
//import be.kdg.prog6.landside.adapters.out.db.SpringDataAppointmentRepository;
//import be.kdg.prog6.landside.adapters.out.db.SpringDataWarehouseProjectionRepository;
//import be.kdg.prog6.landside.adapters.out.db.entities.DeliveryAppointmentJPAEntity;
//import be.kdg.prog6.landside.adapters.out.db.entities.WarehouseJPAEntity;
//import be.kdg.prog6.landside.ports.in.CreateDeliveryAppointmentUseCase;
//import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Optional;
//
//import static be.kdg.prog6.landside.core.TestValues.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class DefaultCreateAppointmentUseCaseIntegrationTest extends AbstractDatabaseTest{
//    @Autowired
//    private CreateDeliveryAppointmentUseCase createDeliveryAppointmentUseCase;
//
//    @Autowired
//    private SpringDataAppointmentRepository appointmentRepository;
//
//    @Autowired
//    private SpringDataWarehouseProjectionRepository warehouseProjectionRepository;
//
//    @BeforeAll
//    static void grantPermissions() throws SQLException {
//        try (Connection connection = DriverManager.getConnection(
//                DATABASE.getJdbcUrl(),
//                "root",
//                "")) {
//            try (Statement statement = connection.createStatement()) {
//                statement.execute("GRANT ALL PRIVILEGES ON *.* TO '" + DATABASE.getUsername() + "'@'%'");
//                statement.execute("FLUSH PRIVILEGES");
//            }
//        }
//    }
//    @Test
//    void shouldCreateAppointment() {
//        // Arrange
//        WarehouseJPAEntity warehouseJPAEntity = new WarehouseJPAEntity();
//        warehouseJPAEntity.setSellerId(SELLER_ID);
//        warehouseProjectionRepository.save(warehouseJPAEntity);
//
//
//        // Act
//        createDeliveryAppointmentUseCase.createAppointment(new CreateDeliveryAppointmentCommand(SELLER_ID, MATERIAL_ID, LICENSE_PLATE, AMOUNT_TONS, APPOINTMENT_TIME));
//
//
//        // Assert
//        final Optional<DeliveryAppointmentJPAEntity> appointmentJPAEntityOptional = appointmentRepository.findFirstByTruckLicensePlateAndDateAndHour(LICENSE_PLATE, APPOINTMENT_TIME.toLocalDate(), APPOINTMENT_TIME.getHour() );
//        assertEquals(appointmentJPAEntityOptional.isPresent(), Boolean.TRUE);
//
//    }
//
//}
