package be.kdg.prog6.landside.ports.in;



import be.kdg.prog6.landside.domain.Warehouse;

import java.util.List;

public interface GetWarehousesUseCase {

    public List<Warehouse> getWarehouses(String sellerName);
}
