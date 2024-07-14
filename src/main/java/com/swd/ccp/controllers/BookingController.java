package com.swd.ccp.controllers;

import com.swd.ccp.DTO.request_models.BookingRequest;
import com.swd.ccp.DTO.request_models.UpdateBookingRequest;
import com.swd.ccp.DTO.response_models.BookingResponse;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.mapper.BookingMapper;
import com.swd.ccp.models.entity_models.Booking;
import com.swd.ccp.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping("/customer/create_booking")
    public ResponseEntity<ResponseObject> createBooking(@RequestBody BookingRequest bookingRequest) {
        ResponseObject responseObject = bookingService.createBooking(bookingRequest);
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(responseObject.getStatusCode()));
    }

    @PutMapping("/customer/{bookingId}/status")
    public ResponseEntity<ResponseObject> updateBookingStatus(
            @PathVariable Integer bookingId,
            @RequestParam String newStatus) {
        ResponseObject responseObject = bookingService.updateBookingStatus(bookingId, newStatus);
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(responseObject.getStatusCode()));
    }
    @PostMapping("/customer/updated_booking")
    public ResponseEntity<ResponseObject> updateBooking(
            @RequestBody UpdateBookingRequest updateBookingRequest) {
        ResponseObject responseObject = bookingService.updateBooking(updateBookingRequest);
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(responseObject.getStatusCode()));
    }


    @GetMapping("/admin/all")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = bookings.stream()
                .map(BookingMapper::bookingToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookingResponses, HttpStatus.OK);
    }

    @GetMapping("/customer/all_booking/{customerId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByCustomerId(@PathVariable Integer customerId) {
        List<Booking> bookings = bookingService.getBookingsByCustomerId(customerId);
        List<BookingResponse> bookingResponses = bookings.stream()
                .map(BookingMapper::bookingToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookingResponses, HttpStatus.OK);
    }

    @GetMapping("/owner/shop/all_booking/{shopId}")
    public ResponseEntity<List<BookingResponse>> getAllBookingsByShopId(@PathVariable Integer shopId) {
        List<Booking> bookings = bookingService.getBookingsByShopId(shopId);
        List<BookingResponse> bookingResponses = bookings.stream()
                .map(BookingMapper::bookingToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookingResponses, HttpStatus.OK);
    }
}