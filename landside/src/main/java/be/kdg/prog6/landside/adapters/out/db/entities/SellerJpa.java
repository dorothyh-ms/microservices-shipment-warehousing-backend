package be.kdg.prog6.landside.adapters.out.db.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(catalog="landside", name="sellers")
public class SellerJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="seller_id")
    private UUID id;

    @Column(unique = true)
    private String name;

    public SellerJpa(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public SellerJpa() {
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

    @Override
    public String toString() {
        return "SellerJpa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
