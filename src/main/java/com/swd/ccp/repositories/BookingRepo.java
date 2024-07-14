package com.swd.ccp.repositories;

import com.swd.ccp.models.entity_models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookingDateAndStartBookingLessThanEqualAndEndBookingGreaterThanEqual(LocalDate bookingDate, LocalTime endBooking, LocalTime startBooking);
    List<Booking> findByBookingStatusNot(String status);

    @Query("SELECT b FROM Booking b WHERE b.bookingStatus <> 'deactive' AND b.account.status <> 'deactive' ")
    List<Booking> findAllActiveBookings();
    @Query("SELECT b FROM Booking b WHERE b.account.id = :customerId AND LOWER(b.bookingStatus) = 'onGoing' AND LOWER(b.account.status) = 'active'")
    List<Booking> findByAccountIdAndActiveStatus(Integer customerId);

    List<Booking> findByPitchShopId(Integer shopId);
    int countByAccountIdAndBookingStatus(int accountId, String bookingStatus);
}
