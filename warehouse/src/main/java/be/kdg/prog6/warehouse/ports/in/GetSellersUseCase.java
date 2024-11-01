package be.kdg.prog6.warehouse.ports.in;

import java.util.List;
import main.java.be.kdg.prog6.warehouse.domain.Seller;

public interface GetSellersUseCase {

    public List<Seller> getSellers();
}
