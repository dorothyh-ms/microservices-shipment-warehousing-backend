package be.kdg.prog6.landside.adapters.in.web.dtos;

import java.util.UUID;

public class CreatePayloadDeliveryTicketDto {

    private UUID materialId;

    private String licensePlate;

    public CreatePayloadDeliveryTicketDto() {
    }

    public CreatePayloadDeliveryTicketDto(UUID materialId, String licensePlate) {
        this.materialId = materialId;
        this.licensePlate = licensePlate;
    }

    public UUID getMaterialId() {
        return materialId;
    }

    public void setMaterialId(UUID materialId) {
        this.materialId = materialId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
