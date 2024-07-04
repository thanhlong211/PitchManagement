package com.swd.ccp.DTO.response_models;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private Integer id;

    private String customerName;

    private String pitchName;
    private String capacity;

    private String bookingStatus;

    private Date createDate;

    private LocalDate bookingDate;

    private String startBooking;

    private String endBooking;

    private float price;

}
