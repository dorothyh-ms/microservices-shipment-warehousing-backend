package be.kdg.prog6.waterside.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="The maximum number of appointments for the selected day has been reached.")
public class DailyBunkeringOperationLimitExceededException extends RuntimeException{
    public DailyBunkeringOperationLimitExceededException(String message) {
        super(message);
    }
}
