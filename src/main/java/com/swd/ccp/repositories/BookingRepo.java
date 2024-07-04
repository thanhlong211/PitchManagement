package com.swd.ccp.repositories;

import com.swd.ccp.models.entity_models.Booking;
import com.swd.ccp.models.entity_models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookingDateAndStartBookingLessThanEqualAndEndBookingGreaterThanEqual(LocalDate bookingDate, LocalTime endBooking, LocalTime startBooking);
}
