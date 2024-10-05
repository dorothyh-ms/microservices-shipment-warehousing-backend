package be.kdg.prog6.warehouse.adapters.out.db;

import be.kdg.prog6.warehouse.ports.out.LoadMaterialPort;
import main.java.be.kdg.prog6.warehouse.domain.Material;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MaterialDbAdapter implements LoadMaterialPort {

    private final SpringDataMaterialRepository materialRepository;

    public MaterialDbAdapter(SpringDataMaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Optional<Material> loadMaterialById(UUID materialId) {
        Optional<MaterialJpaEntity> optionalMaterialJpaEntity = materialRepository.findById(materialId);
        if (optionalMaterialJpaEntity.isPresent()){
            MaterialJpaEntity material = optionalMaterialJpaEntity.get();
            return Optional.of(new Material(
                    material.getId(),
                    material.getName()
            ));
        }
        return Optional.empty();
    }
}
