package com.swd.ccp.repositories;

import com.swd.ccp.models.entity_models.Booking;
import com.swd.ccp.models.entity_models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookingDateAndStartBookingLessThanEqualAndEndBookingGreaterThanEqual(LocalDate bookingDate, LocalTime endBooking, LocalTime startBooking);
    List<Booking> findByBookingStatusNot(String status);

    @Query("SELECT b FROM Booking b WHERE b.bookingStatus <> 'deactive' AND b.customer.account.active <> false ")
    List<Booking> findAllActiveBookings();
    @Query("SELECT b FROM Booking b WHERE b.customer.id = :customerId AND b.bookingStatus <> 'deactive' AND b.customer.account.active <> false ")
    List<Booking> findByCustomerIdAndActiveStatus(Integer customerId);

}
