package be.kdg.prog6.waterside.adapters.in.web.dtos;

import java.util.UUID;

public class NewShippingOrderDto {
   private String purchaseOrderNo;
   private String shippingOrderNo;
   private String vesselNumber;

    public NewShippingOrderDto(String purchaseOrderNo, String shippingOrderNo, String vesselNumber) {
        this.purchaseOrderNo = purchaseOrderNo;
        this.shippingOrderNo = shippingOrderNo;
        this.vesselNumber = vesselNumber;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    @Override
    public String toString() {
        return "NewShippingOrderDto{" +
                "purchaseOrderNo='" + purchaseOrderNo + '\'' +
                ", shippingOrderNo='" + shippingOrderNo + '\'' +
                ", vesselNumber='" + vesselNumber + '\'' +
                '}';
    }
}
