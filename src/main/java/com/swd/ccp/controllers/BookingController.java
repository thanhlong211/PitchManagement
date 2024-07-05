package com.swd.ccp.controllers;

import com.swd.ccp.DTO.request_models.BookingRequest;
import com.swd.ccp.DTO.response_models.BookingResponse;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.mapper.BookingMapper;
import com.swd.ccp.models.entity_models.Booking;
import com.swd.ccp.models.entity_models.Customer;
import com.swd.ccp.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping
    @PreAuthorize("hasAuthority('customer:create')")
    public ResponseEntity<ResponseObject> createBooking(@RequestBody BookingRequest bookingRequest) {
        ResponseObject responseObject = bookingService.createBooking(bookingRequest);
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(responseObject.getStatusCode()));
    }
    @PutMapping("/{bookingId}/status")
    @PreAuthorize("hasAuthority('customer:update')")
    public ResponseEntity<ResponseObject> updateBookingStatus(
            @PathVariable Integer bookingId,
            @RequestParam String newStatus) {
        ResponseObject responseObject = bookingService.updateBookingStatus(bookingId, newStatus);
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(responseObject.getStatusCode()));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('customer:read')")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllActiveBookings();
        List<BookingResponse> bookingResponses = bookings.stream()
                .map(BookingMapper::bookingToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookingResponses, HttpStatus.OK);
    }
}