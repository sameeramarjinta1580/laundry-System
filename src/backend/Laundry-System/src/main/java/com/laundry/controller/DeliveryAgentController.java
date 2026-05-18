package com.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.laundry.dto.request.AgentRequest;
import com.laundry.entity.Booking;
import com.laundry.entity.DeliveryAgent;
import com.laundry.service.DeliveryAgentService;

@RestController
@RequestMapping("/agent")
@CrossOrigin
public class DeliveryAgentController {

    @Autowired
    private DeliveryAgentService service;

    // ✅ ADD AGENT (ADMIN)
    @PostMapping("/add")
    public DeliveryAgent add(@RequestBody AgentRequest request) {
        return service.addAgent(request.getUser(), request.getAgent());
    }
    @GetMapping("/by-user/{userId}")
    public DeliveryAgent getByUser(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    // ✅ GET ACTIVE AGENTS
    @GetMapping
    public List<DeliveryAgent> getAll() {
        return service.getActiveAgents();
    }

    // ✅ ASSIGN AGENT
    @PutMapping("/assign")
    public String assign(@RequestParam Long bookingId,
                         @RequestParam Long agentId) {
        return service.assignAgent(bookingId, agentId);
    }

    // ✅ AGENT VIEW BOOKINGS
    @GetMapping("/bookings/{agentId}")
    public List<Booking> getBookings(@PathVariable Long agentId) {
        return service.getAgentBookings(agentId);
    }

    // ✅ AGENT UPDATE STATUS
    @PutMapping("/status")
    public String updateStatus(@RequestParam Long bookingId,
                               @RequestParam String status) {
        return service.updateStatus(bookingId, status);
    }
}