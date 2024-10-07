package be.kdg.prog6.landside.core;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "be.kdg.prog6", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {
    private static final String DOMAIN_LAYER = "be.kdg.prog6.landside.domain..";
    private static final String ADAPTERS_IN = "be.kdg.prog6.landside.adapters.in..";
    private static final String ADAPTERS_OUT = "be.kdg.prog6.landside.adapters.out..";
    private static final String CORE_LAYER = "be.kdg.prog6.landside.core..";
    private static final String PORTS_IN = "be.kdg.prog6.landside.ports.in..";
    private static final String PORTS_OUT = "be.kdg.prog6.landside.ports.out..";



    private static final String JPA_ENTITIES = "be.kdg.prog6.landside.adapters.out.db.entities..";

    @ArchTest
    static final ArchRule noClassesInAdaptersInShouldDependOnPortsOut =
            noClasses().that().resideInAPackage(ADAPTERS_IN)
                    .should().dependOnClassesThat().resideInAnyPackage(
                            PORTS_OUT
                    )
                    .because("At least one class in the incoming adapters depends on an implementation in the outgoing ports");

    @ArchTest
    static final ArchRule noClassesInAdaptersOutShouldDependOnPortsIn =
            noClasses().that().resideInAPackage(ADAPTERS_OUT)
                    .should().dependOnClassesThat().resideInAnyPackage(
                            PORTS_IN
                    )
                    .because("At least one class in the outbound adapters depends on an implementation in the ingoing ports");


    @ArchTest
    public static final ArchRule noSpringAnnotationsInDomain = noClasses()
            .that().resideInAPackage(DOMAIN_LAYER)
            .should().beAnnotatedWith(Service.class)
            .orShould().beAnnotatedWith(Repository.class)
            .orShould().beAnnotatedWith(Component.class)
            .orShould().beAnnotatedWith(Controller.class);

    @ArchTest
    public static final ArchRule inboundPortsShouldNotDependOnAdapters = noClasses()
            .that().resideInAPackage(PORTS_IN)
            .should().dependOnClassesThat().resideInAnyPackage(ADAPTERS_IN);

    @ArchTest
    public static final ArchRule outboundPortsShouldNotDependOnAdapters = noClasses()
            .that().resideInAPackage(PORTS_OUT)
            .should().dependOnClassesThat().resideInAnyPackage(ADAPTERS_OUT);

    @Test
    void dbEntitiesOnlyAccessibleToOutgoingAdapters() {
        JavaClasses jc = new ClassFileImporter().importPackages("be.kdg.prog6.landside");

        final Architectures.LayeredArchitecture arch = layeredArchitecture().consideringOnlyDependenciesInLayers()
                .layer("DB_ENTITIES").definedBy(JPA_ENTITIES)
                .layer("OUTBOUND_ADAPTERS").definedBy(ADAPTERS_OUT)
                .whereLayer("DB_ENTITIES").mayOnlyBeAccessedByLayers("OUTBOUND_ADAPTERS");

        arch.check(jc);
    }



}

