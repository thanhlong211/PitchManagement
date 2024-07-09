package com.swd.ccp.DTO.request_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    private Integer pitch_id;
    private Integer account_id;
    private LocalDate bookingDate;
    private LocalTime startBooking;
    private LocalTime endBooking;
}
