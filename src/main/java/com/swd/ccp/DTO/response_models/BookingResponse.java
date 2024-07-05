package com.swd.ccp.DTO.response_models;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private Integer id;

    private String customerName;

    private String pitchName;
    private Integer capacity;

    private String bookingStatus;

    private Date createDate;

    private LocalDate bookingDate;

    private LocalTime startBooking;

    private LocalTime endBooking;

    private double price;

}
