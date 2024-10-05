package be.kdg.prog6.warehouse.adapters.out.db;

import be.kdg.prog6.common.events.WarehouseActivityType;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(catalog ="warehouse", name="warehouse_activities")
public class WarehouseActivityJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="warehouse_activity_id")
    private UUID id;


    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private WarehouseActivityType actionType;

    @ManyToOne
    @JoinColumn(name="warehouse_id")
    private WarehouseJpaEntity warehouse;


    @Column(name="amount_tons")
    private double amountTons;

    public WarehouseActivityJpaEntity() {
    }

    public WarehouseActivityJpaEntity(UUID id, WarehouseActivityType type, WarehouseJpaEntity warehouse, double amountTons) {
        this.id = id;
        this.actionType = type;
        this.warehouse = warehouse;
        this.amountTons = amountTons;
    }


    public WarehouseActivityType getActionType() {
        return actionType;
    }

    public void setActionType(WarehouseActivityType action) {
        this.actionType = action;
    }

    public WarehouseJpaEntity getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseJpaEntity warehouse) {
        this.warehouse = warehouse;
    }

    public double getAmountTons() {
        return amountTons;
    }

    public void setAmountTons(double amountTons) {
        this.amountTons = amountTons;
    }
}
