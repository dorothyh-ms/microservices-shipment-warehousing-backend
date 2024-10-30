package be.kdg.prog6.waterside.domain;

import java.util.UUID;

public class ShippingOrder {
    private UUID id;
    private String purchaseOrderNo;
    private String shippingOrderNumber;
    private String vesselNumber;
    private ShippingOrderStatus status;


    public ShippingOrder(UUID id, String shippingOrderNumber, String purchaseOrderNo, String vesselNumber, ShippingOrderStatus status) {
        this.id = id;
        this.shippingOrderNumber = shippingOrderNumber;
        this.purchaseOrderNo = purchaseOrderNo;
        this.vesselNumber = vesselNumber;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }


    public String getVesselNumber() {
        return vesselNumber;
    }


    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getShippingOrderNumber() {
        return shippingOrderNumber;
    }

    public void setShippingOrderNumber(String shippingOrderNumber) {
        this.shippingOrderNumber = shippingOrderNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public ShippingOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ShippingOrderStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "ShippingOrder{" +
                "id=" + id +
                ", purchaseOrderNo='" + purchaseOrderNo + '\'' +
                ", shippingOrderNumber='" + shippingOrderNumber + '\'' +
                ", vesselNumber='" + vesselNumber + '\'' +
                ", status=" + status +
                '}';
    }
}


