package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.exceptions.NoWarehouseAvailableException;
import be.kdg.prog6.landside.ports.in.commands.CreateDeliveryAppointmentCommand;
import be.kdg.prog6.landside.ports.out.AppointmentBookedPort;
import be.kdg.prog6.landside.ports.out.WarehouseLoadPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static be.kdg.prog6.landside.core.TestValues.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultCreateAppointmentUseCaseMockTest {

    private DefaultCreateDeliveryAppointmentUseCase sut;
    private AppointmentBookedPort appointmentBookedPort;
    private WarehouseLoadPort warehouseLoadPort;

    @BeforeEach
    void setUp(){

        appointmentBookedPort = mock(AppointmentBookedPort.class);
        warehouseLoadPort = mock(WarehouseLoadPort.class);

        // Given
        when(warehouseLoadPort.loadWarehousesBySellerId(SELLER_ID)).thenReturn(WAREHOUSE_LIST);
        sut = new DefaultCreateDeliveryAppointmentUseCase(appointmentBookedPort, warehouseLoadPort);
    }
    @Test
    void shouldCreateAppointment() {
        // Act
        sut.createAppointment(new CreateDeliveryAppointmentCommand(SELLER_ID, MATERIAL_ID, LICENSE_PLATE, AMOUNT_TONS, APPOINTMENT_TIME));
        final ArgumentCaptor<Appointment> appointmentCaptor = ArgumentCaptor.forClass(Appointment.class);

        // Assert
        verify(appointmentBookedPort).appointmentBooked(appointmentCaptor.capture());
        assertEquals(LICENSE_PLATE, appointmentCaptor.getValue().getTruckLicensePlate());
        assertEquals(WAREHOUSE, appointmentCaptor.getValue().getWarehouse());
        assertEquals(APPOINTMENT_TIME.getHour(), appointmentCaptor.getValue().getTimeSlotStart().getHour());
        assertEquals(APPOINTMENT_TIME.toLocalDate(), appointmentCaptor.getValue().getTimeSlotStart().toLocalDate());
    }


    @Test
    void createAppointmentShouldFailWithNonExistentSeller(){

        //Assert
        assertThrows(NoWarehouseAvailableException.class, () -> {
            // Act
            sut.createAppointment(new CreateDeliveryAppointmentCommand(UUID.randomUUID(), MATERIAL_ID, LICENSE_PLATE, AMOUNT_TONS, APPOINTMENT_TIME));

        });
    }
}