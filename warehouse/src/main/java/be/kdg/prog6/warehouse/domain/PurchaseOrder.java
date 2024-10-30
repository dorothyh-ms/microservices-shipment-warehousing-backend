package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.PurchaseOrderLine;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import main.java.be.kdg.prog6.warehouse.domain.Seller;
// value object

public class PurchaseOrder {

    private UUID id;
    private String purchaseOrderNumber;
    private String buyerEnterpriseNumber;
    private Seller seller;
    private List<PurchaseOrderLine> orderLines;
    private LocalDateTime orderDateTime;
    private PurchaseOrderStatus status;
    private String vesselNumber;


    public PurchaseOrder(UUID id, String purchaseOrderNumber, String buyerEnterpriseNumber, Seller seller, List<PurchaseOrderLine> orderLines, LocalDateTime orderDateTime, PurchaseOrderStatus status, String vesselNumber) {
        this.id = id;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.seller = seller;
        this.buyerEnterpriseNumber = buyerEnterpriseNumber;
        this.orderLines = orderLines;
        this.orderDateTime = orderDateTime;
        this.status = status;
        this.vesselNumber = vesselNumber;
    }


    public UUID getId() {
        return id;
    }

    public String getBuyerEnterpriseNumber() {
        return buyerEnterpriseNumber;
    }

    public Seller getSeller() {
        return seller;
    }


    public List<PurchaseOrderLine> getOrderLines() {
        return orderLines;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setStatus(PurchaseOrderStatus status) {
        this.status = status;
    }
}
