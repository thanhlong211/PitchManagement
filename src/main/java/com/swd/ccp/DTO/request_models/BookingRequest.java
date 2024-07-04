package com.swd.ccp.DTO.request_models;

import com.swd.ccp.models.entity_models.Customer;
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
    private Integer pitchId;
    private Integer customerId;
    private LocalDate bookingDate;
    private LocalTime startBooking;
    private LocalTime endBooking;
}
