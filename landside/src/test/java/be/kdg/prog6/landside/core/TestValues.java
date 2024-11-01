package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.landside.domain.Seller;
import be.kdg.prog6.landside.domain.Warehouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestValues {

    public static final UUID SELLER_ID = UUID.randomUUID();
    public static final String SELLER_NAME = "integration_test_seller";
    public static final Material MATERIAL = Material.CEMENT;

    public static final String LICENSE_PLATE = "test";

    public static final double AMOUNT_TONS = 10;

    public static final LocalDateTime APPOINTMENT_TIME = LocalDateTime.now();

    public static final Warehouse WAREHOUSE = new Warehouse(
            new Seller(UUID.randomUUID(), SELLER_NAME));

    public static final List<Warehouse> WAREHOUSE_LIST = new ArrayList<>(Arrays.asList(WAREHOUSE));




    private TestValues(){};


}
