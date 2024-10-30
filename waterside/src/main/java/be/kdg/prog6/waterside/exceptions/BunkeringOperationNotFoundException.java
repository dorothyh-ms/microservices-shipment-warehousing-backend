package be.kdg.prog6.waterside.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="BunkeringOperation not found")
public class BunkeringOperationNotFoundException extends RuntimeException {

    public BunkeringOperationNotFoundException(String message){
        super(message);
    }
}
