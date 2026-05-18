package com.laundry.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.laundry.entity.Booking;
import com.laundry.repository.BookingRepository;
import com.laundry.service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BookingRepository bookingRepo;
   
    @PostMapping("/create-order")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody Map<String, Object> req) {

        Long bookingId = Long.parseLong(req.get("bookingId").toString());
        Double amount = Double.parseDouble(req.get("amount").toString());
        String email = req.get("email").toString();
        String phone = req.get("phone").toString();

        Map<String, String> response =
                paymentService.createOrder(bookingId, amount, email, phone);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody Map<String, String> data) {

        String orderId = data.get("orderId");
        String status = data.get("status");

        if ("success".equalsIgnoreCase(status)) {
            paymentService.handlePaymentSuccess(orderId);
        } else {
            paymentService.handlePaymentFailure(orderId);
        }

        return ResponseEntity.ok("Payment processed");
    }
}