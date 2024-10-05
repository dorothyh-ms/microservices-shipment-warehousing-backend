package be.kdg.prog6.warehouse.adapters.out.db;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(catalog="warehouse", name="sellers")
public class SellerJpaEntity {
    @Id
    private UUID id;

    @Column
    private String name;

    public SellerJpaEntity() {
    }

    public SellerJpaEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
