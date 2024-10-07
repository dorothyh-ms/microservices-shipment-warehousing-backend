package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Warehouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestClasses {

    public static final UUID SELLER_ID = UUID.randomUUID();
    public static final UUID MATERIAL_ID = UUID.randomUUID();

    public static final String LICENSE_PLATE = "test";

    public static final double AMOUNT_TONS = 10;

    public static final LocalDateTime APPOINTMENT_TIME = LocalDateTime.now();

    public static final Warehouse WAREHOUSE = new Warehouse(SELLER_ID);

    public static final List<Warehouse> WAREHOUSE_LIST = new ArrayList<>(Arrays.asList(WAREHOUSE));




    private TestClasses(){};


}
