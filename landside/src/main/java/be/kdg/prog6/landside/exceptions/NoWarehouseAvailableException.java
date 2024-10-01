package be.kdg.prog6.landside.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="")
public class NoWarehouseAvailableException extends RuntimeException{
    public NoWarehouseAvailableException(String message){
        super(message);
    }
}
