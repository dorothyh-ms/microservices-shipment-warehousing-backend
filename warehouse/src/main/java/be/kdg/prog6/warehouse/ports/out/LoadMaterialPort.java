package be.kdg.prog6.warehouse.ports.out;
import main.java.be.kdg.prog6.warehouse.domain.Material;

import java.util.Optional;
import java.util.UUID;

public interface LoadMaterialPort {
    Optional<Material> loadMaterialById(UUID materialId);
}
