package be.kdg.prog6.waterside.adapters.out.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog="waterside", name="bunkering_operations")
public class BunkeringOperationJpaEntity {
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name="shipping_order_id")
    private ShippingOrderJpaEntity shippingOrder;

    @Column(name="completed_date")
    private LocalDateTime completedDate;

    @Column(name="planned_date")
    private LocalDate plannedDate;

    public BunkeringOperationJpaEntity() {
    }

    public BunkeringOperationJpaEntity(UUID id, ShippingOrderJpaEntity shippingOrder) {
        this.id = id;
        this.shippingOrder = shippingOrder;
    }

    public BunkeringOperationJpaEntity(UUID id, ShippingOrderJpaEntity shippingOrder, LocalDateTime completedDate, LocalDate plannedDate) {
        this.id = id;
        this.shippingOrder = shippingOrder;
        this.completedDate = completedDate;
        this.plannedDate = plannedDate;
    }

    public UUID getId() {
        return id;
    }

    public ShippingOrderJpaEntity getShippingOrder() {
        return shippingOrder;
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public LocalDate getPlannedDate() {
        return plannedDate;
    }
}
