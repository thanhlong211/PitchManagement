package com.swd.ccp.services_implementors;

import com.swd.ccp.DTO.request_models.BookingRequest;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.models.entity_models.Booking;
import com.swd.ccp.models.entity_models.Customer;
import com.swd.ccp.models.entity_models.Pitch;
import com.swd.ccp.repositories.AccountRepo;
import com.swd.ccp.repositories.BookingRepo;
import com.swd.ccp.repositories.CustomerRepo;
import com.swd.ccp.repositories.PitchRepo;
import com.swd.ccp.services.BookingService;
import com.swd.ccp.services.CustomerService;
import com.swd.ccp.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepository;
    private final JWTService jwtService;
    private final CustomerRepo customerRepo;
    private final PitchRepo pitchRepo;
    @Override
    public ResponseObject createBooking(BookingRequest bookingRequest) {

        Optional<Customer> customerOptional = customerRepo.findById(bookingRequest.getCustomerId());
        Customer customer = customerOptional.orElseThrow(() -> new IllegalArgumentException("No customer information found."));

        Optional<Pitch> pitchOptional = pitchRepo.findById(bookingRequest.getPitchId());
        Pitch pitch = pitchOptional.orElseThrow(() -> new IllegalArgumentException("No pitch information found."));

        if (bookingRequest.getBookingDate().isBefore(LocalDate.now())) {
            return ResponseObject.builder()
                    .message("Fields cannot be set to dates in the past.")
                    .statusCode(400)
                    .build();
        }
        if (bookingRequest.getStartBooking().compareTo(bookingRequest.getEndBooking()) >= 0) {
            return ResponseObject.builder()
                    .message("The start time must be less than the end time.")
                    .statusCode(400)
                    .build();
        }

        // Kiểm tra các booking xung đột
        List<Booking> conflictingBookings = bookingRepository.findByBookingDateAndStartBookingLessThanEqualAndEndBookingGreaterThanEqual(
                bookingRequest.getBookingDate(),
                bookingRequest.getEndBooking(),
                bookingRequest.getStartBooking()
        );
        if (!conflictingBookings.isEmpty()) {
            return ResponseObject.builder()
                    .message("It is not possible to reserve the field at this time because it is already booked.")
                    .statusCode(400)
                    .build();
        }

        // Tính toán thời gian đặt sân (giả sử startBooking và endBooking là kiểu LocalTime)
        long hours = Duration.between(bookingRequest.getStartBooking(), bookingRequest.getEndBooking()).toHours();
        if (hours <= 0) {
            return ResponseObject.builder()
                    .message("Invalid pitch time.")
                    .statusCode(400)
                    .build();
        }

        // Tính tổng giá tiền
        double totalPrice = hours * pitch.getPrice();

        // Tạo mới đối tượng Booking
        Booking newBooking = Booking.builder()
                .customer(customer)
                .pitch(pitch)
                .bookingStatus("onGoing")
                .createDate(new Date(System.currentTimeMillis()))
                .bookingDate(bookingRequest.getBookingDate())
                .startBooking(bookingRequest.getStartBooking())
                .endBooking(bookingRequest.getEndBooking())
                .price(totalPrice) // Gán tổng giá tiền cho booking
                .build();

        bookingRepository.save(newBooking);

        return ResponseObject.builder()
                .message("Booking created successfully.")
                .statusCode(201)
                .build();
    }

    @Override
    public ResponseObject updateBookingStatus(Integer bookingId, String newStatus) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (!bookingOptional.isPresent()) {
            return ResponseObject.builder()
                    .message("Booking not found")
                    .statusCode(404)
                    .build();
        }

        Booking booking = bookingOptional.get();
        booking.setBookingStatus(newStatus);
        bookingRepository.save(booking);

        return ResponseObject.builder()
                .message("Booking status updated successfully.")
                .statusCode(200)
                .build();
    }
    @Override
    public List<Booking> getAllActiveBookings() {
        return bookingRepository.findAllActiveBookings();
    }
    @Override
    public List<Booking> getBookingsByCustomerId(Integer customerId) {
        return bookingRepository.findByCustomerIdAndActiveStatus(customerId);
    }
}
