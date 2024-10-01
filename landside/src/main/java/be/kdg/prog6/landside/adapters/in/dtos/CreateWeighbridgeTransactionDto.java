package be.kdg.prog6.landside.adapters.in.dtos;

public class CreateWeighbridgeTransactionDto {

    private double truckInitialWeightTons;

    public CreateWeighbridgeTransactionDto(double truckInitialWeightTons) {
        this.truckInitialWeightTons = truckInitialWeightTons;
    }

    public CreateWeighbridgeTransactionDto() {
    }

    public double getTruckInitialWeightTons() {
        return truckInitialWeightTons;
    }

    public void setTruckInitialWeightTons(double truckInitialWeightTons) {
        this.truckInitialWeightTons = truckInitialWeightTons;
    }
}
