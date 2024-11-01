package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.ports.in.GetSellersUseCase;
import be.kdg.prog6.warehouse.ports.out.LoadSellerPort;
import main.java.be.kdg.prog6.warehouse.domain.Seller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetSellersUseCase implements GetSellersUseCase {
    private final LoadSellerPort sellerLoadPort;

    public DefaultGetSellersUseCase(LoadSellerPort sellerLoadPort) {
        this.sellerLoadPort = sellerLoadPort;
    }

    @Override
    public List<Seller> getSellers() {
        return sellerLoadPort.loadSellers();
    }
}
