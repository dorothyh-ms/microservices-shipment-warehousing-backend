package be.kdg.prog6.waterside.adapters.in.web.dtos;

public class CompleteInspectionOperationDto {
    private String signature;

    public CompleteInspectionOperationDto() {
    }

    public CompleteInspectionOperationDto(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "CompleteInspectionOperationDto{" +
                "signature='" + signature + '\'' +
                '}';
    }


}
