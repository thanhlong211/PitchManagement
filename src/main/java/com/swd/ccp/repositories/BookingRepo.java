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

    @Query("SELECT b FROM Booking b WHERE "
            + "(:customerName IS NULL OR b.customer.account.name LIKE %:customerName%) AND "
            + "(:pitchName IS NULL OR b.pitch.name LIKE %:pitchName%) AND "
            + "(:bookingDate IS NULL OR b.bookingDate = :bookingDate) AND "
            + "b.bookingStatus != 'deactive'")
    List<Booking> searchBookings(@Param("customerName") String customerName,
                                 @Param("pitchName") String pitchName,
                                 @Param("bookingDate") LocalDate bookingDate);

}
