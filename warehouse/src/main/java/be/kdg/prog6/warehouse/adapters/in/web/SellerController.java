package be.kdg.prog6.warehouse.adapters.in.web;


import be.kdg.prog6.warehouse.adapters.in.web.dtos.SellerDto;
import be.kdg.prog6.warehouse.ports.in.GetSellersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import main.java.be.kdg.prog6.warehouse.domain.Seller;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final GetSellersUseCase getSellersUseCase;

    public SellerController(GetSellersUseCase getSellersUseCase) {
        this.getSellersUseCase = getSellersUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('warehouse_manager')")
    public ResponseEntity<List<SellerDto>> getSellers() {
        List<Seller> sellers = getSellersUseCase.getSellers();

        return new ResponseEntity<>(
                sellers.stream().map(seller -> new SellerDto(seller.getId(), seller.getName())).toList(),
                HttpStatus.OK);
    }
}
