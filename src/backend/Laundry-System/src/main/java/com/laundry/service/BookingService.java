package com.laundry.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.dto.request.BookingRequest;
import com.laundry.dto.response.BookingResponse;
import com.laundry.entity.Booking;
import com.laundry.entity.ServiceEntity;
import com.laundry.repository.BookingRepository;
import com.laundry.repository.ServiceRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private ServiceRepository serviceRepo;

    // ✅ CREATE BOOKING
    public BookingResponse createBooking(BookingRequest req) {

        ServiceEntity service = serviceRepo.findById(req.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));
        if (req.getShirtCount() == 0 &&
        	    req.getPantCount() == 0 &&
        	    (req.getOtherItems() == null || req.getOtherItems().isEmpty())) {

        	    throw new RuntimeException("At least one item is required");
        	}

        // 👉 TOTAL ITEMS
        int totalItems = req.getShirtCount() + req.getPantCount();

     // simple logic: count extra items manually if provided
     if (req.getOtherItems() != null && !req.getOtherItems().isEmpty()) {
         totalItems += 2; // basic handling (safe for demo)
     }

        // 👉 PRICE CALCULATION
        double totalPrice = service.getPricePerItem() * totalItems;

        Booking booking = new Booking();

        if (req.getUserId() == null) {
            throw new RuntimeException("User ID cannot be null");
        }
        booking.setUserId(req.getUserId());
        booking.setDeliveryAgentId(null); // initially no agent assigned
        booking.setServiceId(req.getServiceId());
        

        // ⭐ ADD THIS (if using branch)
        booking.setBranchId(req.getBranchId());

        booking.setShirtCount(req.getShirtCount());
        booking.setPantCount(req.getPantCount());
        booking.setOtherItems(req.getOtherItems());
        booking.setTotalPrice(totalPrice);

        booking.setDeliveryType(req.getDeliveryType());
        booking.setPaymentType(req.getPaymentType());

        // 🔥 UPDATED PAYMENT LOGIC
        if (req.getPaymentType().equalsIgnoreCase("COD")) {
            booking.setPaymentStatus("PENDING");
        } else {
            booking.setPaymentStatus("PENDING"); // ONLINE → wait for success
        }

        booking.setOrderStatus("PLACED");
        booking.setAddress(req.getAddress());

        booking.setDeliveryDays(2);
        booking.setBookingDate(LocalDateTime.now());

        // ⭐ IMPORTANT: Generate Order ID
        String orderId = "ORD_" + System.currentTimeMillis();
        booking.setOrderId(orderId);

        Booking saved = bookingRepo.save(booking);

        return mapToResponse(saved);
    }

    // ✅ USER BOOKINGS
    public List<BookingResponse> getUserBookings(Long userId) {

        return bookingRepo.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ GET BY ID
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToResponse(booking);
    }

    // ✅ CANCEL BOOKING
    public void cancelBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setOrderStatus("CANCELLED");
        booking.setPaymentStatus("FAILED"); // ⭐ IMPORTANT

        bookingRepo.save(booking);
    }

    // ✅ PAYMENT SUCCESS
    public void updatePaymentSuccess(String orderId) {

        Booking booking = bookingRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setPaymentStatus("PAID");
        booking.setOrderStatus("CONFIRMED");

        bookingRepo.save(booking);
    }

    // ⭐ NEW: PAYMENT FAILED (IMPORTANT)
    public void updatePaymentFailed(String orderId) {

        Booking booking = bookingRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setPaymentStatus("FAILED");
        booking.setOrderStatus("CANCELLED");

        bookingRepo.save(booking);
    }

    // 🔁 COMMON RESPONSE
    public BookingResponse mapToResponse(Booking b) {

        BookingResponse res = new BookingResponse();

        res.setId(b.getId());
        res.setUserId(b.getUserId());
        res.setServiceId(b.getServiceId());
        res.setBranchId(b.getBranchId());

        res.setShirtCount(b.getShirtCount());
        res.setPantCount(b.getPantCount());
        res.setTotalPrice(b.getTotalPrice());

        res.setPaymentType(b.getPaymentType());
        res.setPaymentStatus(b.getPaymentStatus());
        res.setDeliveryType(b.getDeliveryType());
        res.setOrderStatus(b.getOrderStatus());

        res.setAddress(b.getAddress());
        res.setDeliveryDays(b.getDeliveryDays());
        res.setBookingDate(b.getBookingDate());
        res.setOrderId(b.getOrderId());
        res.setOtherItems(b.getOtherItems());

        // ✅ NEW DATA (SAFE - optional for frontend)
        if (b.getService() != null) {
            res.setServiceName(b.getService().getName());
        }

        if (b.getBranch() != null) {
            res.setBranchName(b.getBranch().getName());
        }

        if (b.getUser() != null) {
            res.setUserName(b.getUser().getName());
            res.setUserPhone(b.getUser().getPhone());
        }

        if (b.getAgent() != null && b.getAgent().getUser() != null) {
            res.setAgentName(b.getAgent().getUser().getName());
            res.setAgentPhone(b.getAgent().getUser().getPhone());
        }

        return res;
    }
}