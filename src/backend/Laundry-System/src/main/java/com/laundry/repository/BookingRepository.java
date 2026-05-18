package com.laundry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // 👉 Get bookings by user
    List<Booking> findByUserId(Long userId);

    // 👉 Admin: filter by status
    List<Booking> findByOrderStatus(String status);
    List<Booking> findByUserIdOrderByBookingDateDesc(Long userId);
    long count();
    Optional<Booking> findByOrderId(String orderId);
    List<Booking> findByDeliveryAgentId(Long agentId);
}