package be.kdg.prog6.warehouse.adapters.in.web.dtos;

import be.kdg.prog6.common.domain.PurchaseOrderLine;
import be.kdg.prog6.warehouse.domain.PurchaseOrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PurchaseOrderDto {

    private UUID id;
    private String purchaseOrderNumber;
    private String buyerEnterpriseNumber;
    private UUID sellerId;
    private String sellerName;
    private List<PurchaseOrderLine> orderLines;
    private LocalDateTime orderDateTime;
    private PurchaseOrderStatus status;

    public PurchaseOrderDto(UUID id, String purchaseOrderNumber, String buyerEnterpriseNumber, UUID sellerId, String sellerName, List<PurchaseOrderLine> orderLines, LocalDateTime orderDateTime, PurchaseOrderStatus status) {
        this.id = id;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.buyerEnterpriseNumber = buyerEnterpriseNumber;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.orderLines = orderLines;
        this.orderDateTime = orderDateTime;
        this.status = status;
    }

    public PurchaseOrderDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getBuyerEnterpriseNumber() {
        return buyerEnterpriseNumber;
    }

    public void setBuyerEnterpriseNumber(String buyerEnterpriseNumber) {
        this.buyerEnterpriseNumber = buyerEnterpriseNumber;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<PurchaseOrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<PurchaseOrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderStatus status) {
        this.status = status;
    }
}
