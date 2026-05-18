package com.laundry.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundry.entity.Booking;
import com.laundry.repository.BookingRepository;

@Service
public class PaymentService {

    @Value("${cashfree.appId}")
    private String appId;

    @Value("${cashfree.secretKey}")
    private String secretKey;

    @Value("${cashfree.baseUrl}")
    private String baseUrl;

    @Autowired
    private BookingRepository bookingRepo; // ✅ REQUIRED
    @Autowired
    private BookingService bookingservice;

    public Map<String, String> createOrder(Long bookingId, Double amount, String email, String phone) {

        try {

            // ✅ FETCH BOOKING
            Booking booking = bookingRepo.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found"));

            // ✅ USE SAME ORDER ID (VERY IMPORTANT)
            String orderId = booking.getOrderId();

            String requestBody = "{\n" +
                    "\"order_id\": \"" + orderId + "\",\n" +
                    "\"order_amount\": " + amount + ",\n" +
                    "\"order_currency\": \"INR\",\n" +
                    "\"customer_details\": {\n" +
                    "   \"customer_id\": \"cust_" + bookingId + "\",\n" +
                    "   \"customer_email\": \"" + email + "\",\n" +
                    "   \"customer_phone\": \"" + phone + "\"\n" +
                    "},\n" +
                    "\"order_meta\": {\n" +
                    "   \"return_url\": \"http://localhost:5173/payment-success?order_id={order_id}\"\n" +
                    "}\n" +
                    "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl))
                    .header("x-client-id", appId)
                    .header("x-client-secret", secretKey)
                    .header("x-api-version", "2022-09-01")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // ❌ if API fails
            if (response.statusCode() != 200) {
                throw new RuntimeException("Cashfree Error: " + response.body());
            }

            // ✅ Parse response
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> res =
                    mapper.readValue(response.body(), Map.class);

            String sessionId = (String) res.get("payment_session_id");

            // ✅ RETURN DATA TO FRONTEND
            Map<String, String> result = new HashMap<>();
            result.put("sessionId", sessionId);
            result.put("orderId", orderId);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Payment Error: " + e.getMessage());
        }
    }
    public void handlePaymentSuccess(String orderId) {
        bookingservice.updatePaymentSuccess(orderId);
    }

    public void handlePaymentFailure(String orderId) {
        bookingservice.updatePaymentFailed(orderId);
    }
}