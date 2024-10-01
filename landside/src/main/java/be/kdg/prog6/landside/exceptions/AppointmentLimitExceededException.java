package be.kdg.prog6.landside.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Exceeded appointment limit for requested timeslot")
public class AppointmentLimitExceededException extends RuntimeException{
    public AppointmentLimitExceededException(String message){
        super(message);
    }
}
