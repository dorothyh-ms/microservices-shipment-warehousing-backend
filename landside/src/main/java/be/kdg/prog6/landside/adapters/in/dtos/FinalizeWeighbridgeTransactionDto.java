package be.kdg.prog6.landside.adapters.in.dtos;

import java.time.LocalDateTime;

public class FinalizeWeighbridgeTransactionDto {


    private double truckFinalWeightTons;



    public FinalizeWeighbridgeTransactionDto() {
    }

    public FinalizeWeighbridgeTransactionDto(double truckFinalWeightTons) {
        this.truckFinalWeightTons = truckFinalWeightTons;

    }

    public double getTruckFinalWeightTons() {
        return truckFinalWeightTons;
    }

    public void setTruckFinalWeightTons(double truckFinalWeightTons) {
        this.truckFinalWeightTons = truckFinalWeightTons;
    }


}
