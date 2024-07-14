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
public class UpdateBookingRequest {
    private Integer customerId;
    private LocalDate bookingDate;
    private LocalTime startBooking;
    private LocalTime endBooking;
}
