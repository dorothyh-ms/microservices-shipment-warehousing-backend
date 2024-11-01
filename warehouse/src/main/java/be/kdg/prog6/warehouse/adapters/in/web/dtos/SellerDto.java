package be.kdg.prog6.warehouse.adapters.in.web.dtos;

import java.util.UUID;

public class SellerDto {
    private UUID id;
    private String name;

    public SellerDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public SellerDto() {
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
