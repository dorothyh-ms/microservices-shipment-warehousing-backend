package be.kdg.prog6.waterside.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="ShippingOrder not found")
public class ShippingOrderNotFoundException extends RuntimeException {
  public ShippingOrderNotFoundException(String message) {
    super(message);
  }
}
