package com.swd.ccp.DTO.request_models;

import java.time.LocalDate;
import java.time.LocalTime;

public class AvailablePitchRequest {
    private LocalDate bookingDate;
    private LocalTime startBooking;
    private LocalTime endBooking;

    // Getters and setters
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getStartBooking() {
        return startBooking;
    }

    public void setStartBooking(LocalTime startBooking) {
        this.startBooking = startBooking;
    }

    public LocalTime getEndBooking() {
        return endBooking;
    }

    public void setEndBooking(LocalTime endBooking) {
        this.endBooking = endBooking;
    }
}
