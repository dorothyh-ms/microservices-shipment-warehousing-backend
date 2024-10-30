package be.kdg.prog6.waterside.adapters.in.web.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class InspectionOperationDto {

    private UUID id;
    private String vesselNumber;

    private LocalDateTime inspectionDate;

    private String inspectorSignature;

    public InspectionOperationDto() {
    }

    public InspectionOperationDto(UUID id, String vesselNumber, LocalDateTime inspectionDate, String inspectorSignature) {
        this.id = id;
        this.vesselNumber = vesselNumber;
        this.inspectionDate = inspectionDate;
        this.inspectorSignature = inspectorSignature;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDateTime inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getInspectorSignature() {
        return inspectorSignature;
    }

    public void setInspectorSignature(String inspectorSignature) {
        this.inspectorSignature = inspectorSignature;
    }
}
