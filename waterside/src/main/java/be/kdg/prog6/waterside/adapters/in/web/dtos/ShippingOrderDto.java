package be.kdg.prog6.waterside.adapters.in.web.dtos;

import be.kdg.prog6.waterside.domain.ShippingOrderStatus;

import java.util.UUID;

public class ShippingOrderDto {

    private UUID id;
    private String purchaseOrderNo;
    private String shippingOrderNumber;
    private String vesselNumber;
    private String status;

    public ShippingOrderDto(UUID id, String purchaseOrderNo, String shippingOrderNumber, String vesselNumber, String status) {
        this.id = id;
        this.purchaseOrderNo = purchaseOrderNo;
        this.shippingOrderNumber = shippingOrderNumber;
        this.vesselNumber = vesselNumber;
        this.status = status;
    }

    public ShippingOrderDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
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

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
