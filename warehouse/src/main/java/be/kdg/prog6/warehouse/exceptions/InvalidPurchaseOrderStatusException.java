package be.kdg.prog6.warehouse.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Requested status was invalid")
public class InvalidPurchaseOrderStatusException extends RuntimeException {
  public InvalidPurchaseOrderStatusException(String message) {
    super(message);
  }
}
