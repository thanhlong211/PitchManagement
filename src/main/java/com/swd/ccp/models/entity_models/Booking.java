package com.swd.ccp.models.entity_models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "pitch_id")
    private Pitch pitch;

    private String bookingStatus;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "start_booking")
    private LocalTime startBooking;

    @Column(name = "end_booking")
    private LocalTime endBooking;

    private double price;
}
