package be.kdg.prog6.waterside.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Mandatory operations not completed")
public class MandatoryOperationsNotCompletedException extends RuntimeException {
    public MandatoryOperationsNotCompletedException(String message) {
        super(message);
    }
}
