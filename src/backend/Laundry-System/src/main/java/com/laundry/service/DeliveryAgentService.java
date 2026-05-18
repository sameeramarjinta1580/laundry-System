package com.laundry.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.entity.Booking;
import com.laundry.entity.DeliveryAgent;
import com.laundry.entity.User;
import com.laundry.repository.BookingRepository;
import com.laundry.repository.DeliveryAgentRepository;
import com.laundry.repository.UserRepository;

@Service
public class DeliveryAgentService {

    @Autowired
    private DeliveryAgentRepository agentRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookingRepository bookingRepo;

    // ✅ ADD AGENT
    public DeliveryAgent addAgent(User userRequest, DeliveryAgent agentRequest) {

        // 🔥 Create USER
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setPassword("123456");
        user.setRole("AGENT");

        userRepo.save(user);

        // 🔥 Create Agent
        DeliveryAgent agent = new DeliveryAgent();
        agent.setUser(user);
        agent.setAddress(agentRequest.getAddress());
        agent.setCity(agentRequest.getCity());
        agent.setActive(true);

        return agentRepo.save(agent);
    }
    
    public DeliveryAgent getByUserId(Long userId) {
        return agentRepo.findByUserId(userId);
    }

    // ✅ GET ACTIVE AGENTS
    public List<DeliveryAgent> getActiveAgents() {
        return agentRepo.findByActiveTrue();
    }

    // ✅ ADMIN ASSIGNS AGENT
    public String assignAgent(Long bookingId, Long agentId) {

        Booking booking = bookingRepo.findById(bookingId).orElseThrow();
        DeliveryAgent agent = agentRepo.findById(agentId).orElseThrow();

        booking.setDeliveryAgentId(agentId);
        booking.setOrderStatus("ASSIGNED");

        bookingRepo.save(booking);

        return "Agent Assigned Successfully";
    }

    // ✅ AGENT GET THEIR BOOKINGS
    public List<Booking> getAgentBookings(Long agentId) {
        return bookingRepo.findByDeliveryAgentId(agentId);
    }

    // ✅ AGENT UPDATE STATUS
    public String updateStatus(Long bookingId, String status) {

        Booking booking = bookingRepo.findById(bookingId).orElseThrow();

        booking.setOrderStatus(status);

        if (status.equalsIgnoreCase("PICKED_UP")) {
            booking.setPickupTime(LocalDateTime.now());
        }

        if (status.equalsIgnoreCase("DELIVERED")) {
            booking.setDeliveryTime(LocalDateTime.now());

            if ("COD".equalsIgnoreCase(booking.getPaymentType())) {
                booking.setPaymentStatus("PAID");
            }
        }

        bookingRepo.save(booking);

        return "Status Updated";
    }
}