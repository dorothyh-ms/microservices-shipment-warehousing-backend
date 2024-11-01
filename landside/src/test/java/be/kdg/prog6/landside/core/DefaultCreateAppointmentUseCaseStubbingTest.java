package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.exceptions.NoWarehouseAvailableException;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static be.kdg.prog6.landside.core.TestValues.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefaultCreateAppointmentUseCaseStubbingTest {

    private WarehouseLoadPortStub warehouseLoadPortStub;
    private AppointmentBookedPortStub appointmentBookedPortStub;

    private DefaultCreateDeliveryAppointmentUseCase sut;




    @BeforeEach
    void setup() {
        warehouseLoadPortStub = new WarehouseLoadPortStub();
        appointmentBookedPortStub =new AppointmentBookedPortStub();
        sut = new DefaultCreateDeliveryAppointmentUseCase(appointmentBookedPortStub, warehouseLoadPortStub);

    }

    @Test
    void shouldCreateAppointment() {
        sut.createAppointment(new CreateDeliveryAppointmentCommand(SELLER_NAME, MATERIAL, LICENSE_PLATE, AMOUNT_TONS, APPOINTMENT_TIME));
        assertEquals(AMOUNT_TONS, appointmentBookedPortStub.getAppointment().getAmountTons() );
        assertEquals(MATERIAL, appointmentBookedPortStub.getAppointment().getMaterial());
        assertEquals(APPOINTMENT_TIME.getHour(), appointmentBookedPortStub.getAppointment().getTimeSlotStart().getHour());
        assertEquals(APPOINTMENT_TIME.toLocalDate(), appointmentBookedPortStub.getAppointment().getTimeSlotStart().toLocalDate());
        assertEquals(LICENSE_PLATE, appointmentBookedPortStub.getAppointment().getTruckLicensePlate());
        assertEquals(SELLER_NAME, appointmentBookedPortStub.getAppointment().getWarehouse().getSeller().getName());
        assertEquals(AMOUNT_TONS, appointmentBookedPortStub.getAppointment().getAmountTons());
    }

    @Test
    void createAppointmentShouldFailWithNonExistentSeller(){

        String sellerName = "nonexistentseller";
        //Assert
        assertThrows(NoWarehouseAvailableException.class, () -> {
            // Act
            sut.createAppointment(new CreateDeliveryAppointmentCommand(sellerName, MATERIAL, LICENSE_PLATE, AMOUNT_TONS, APPOINTMENT_TIME));

        });
    }
}
