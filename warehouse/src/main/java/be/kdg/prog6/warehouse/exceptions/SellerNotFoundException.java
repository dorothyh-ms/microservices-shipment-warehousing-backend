package be.kdg.prog6.warehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Seller not found")
public class SellerNotFoundException extends RuntimeException{
}
