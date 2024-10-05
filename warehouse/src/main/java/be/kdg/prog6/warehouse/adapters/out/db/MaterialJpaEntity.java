package be.kdg.prog6.warehouse.adapters.out.db;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(catalog = "warehouse", name="material")
public class MaterialJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column
    private String name;

    public MaterialJpaEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public MaterialJpaEntity() {
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
