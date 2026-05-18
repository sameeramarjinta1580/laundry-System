package com.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.laundry.dto.request.BookingRequest;
import com.laundry.dto.response.BookingResponse;
import com.laundry.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // ✅ CREATE BOOKING
    @PostMapping("/create")
    public BookingResponse createBooking(@Valid @RequestBody BookingRequest req) {
        return bookingService.createBooking(req);
    }

    // ✅ USER BOOKINGS
    @GetMapping("/user/{userId}")
    public List<BookingResponse> getUserBookings(@PathVariable Long userId) {
        return bookingService.getUserBookings(userId);
    }

    // ✅ GET BOOKING BY ID (NEW - useful for UI/payment)
    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // ✅ PAYMENT SUCCESS (NEW - IMPORTANT)
    @PostMapping("/payment-success")
    public String paymentSuccess(@RequestParam String orderId) {
        bookingService.updatePaymentSuccess(orderId);
        return "Payment successful & booking confirmed";
    }

    // ❌ OPTIONAL (if you want cancel feature)
    @DeleteMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "Booking cancelled successfully";
    }
}