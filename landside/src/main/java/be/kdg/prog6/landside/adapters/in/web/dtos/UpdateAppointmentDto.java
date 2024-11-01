package be.kdg.prog6.landside.adapters.in.web.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UpdateAppointmentDto {


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeSlot;

    public UpdateAppointmentDto(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }
}
