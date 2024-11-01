package be.kdg.prog6.landside.adapters.in.web;

import be.kdg.prog6.landside.adapters.in.web.dtos.UnavailableDateDto;
import be.kdg.prog6.landside.ports.in.GetUnavailableDatesUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/unavailable-appointment-dates")
public class UnavailableDatesController {

    private GetUnavailableDatesUseCase getUnavailableDatesUseCase;


    public UnavailableDatesController(GetUnavailableDatesUseCase getUnavailableDatesUseCase) {
        this.getUnavailableDatesUseCase = getUnavailableDatesUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('warehouse_manager') or hasAuthority('seller')")
    public ResponseEntity<List<UnavailableDateDto>> loadUnavailableDatesForAppointment(@AuthenticationPrincipal Jwt token) {
        List<LocalDate> dates = getUnavailableDatesUseCase.getInvalidDatesForAppointment();
        return new ResponseEntity<>(dates
                .stream()
                .map(UnavailableDateDto::new).toList()
        , HttpStatus.OK);
    }
}
