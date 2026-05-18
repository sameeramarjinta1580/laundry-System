package com.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.laundry.dto.request.AgentRequest;
import com.laundry.dto.response.AdminDashboardResponse;
import com.laundry.dto.response.BookingResponse;
import com.laundry.entity.Booking;
import com.laundry.entity.DeliveryAgent;
import com.laundry.entity.User;
import com.laundry.service.AdminService;
import com.laundry.service.DeliveryAgentService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;
    private DeliveryAgentService deliveryagentservice;
  

    // ✅ DASHBOARD API
    @GetMapping("/dashboard")
    public AdminDashboardResponse getDashboard() {
        return adminService.getDashboardStats();
    }

    // ✅ GET ALL BOOKINGS
    @GetMapping("/bookings")
    public List<BookingResponse> getAllBookings() {
        return adminService.getAllBookings();
    }
    // ✅ UPDATE ORDER STATUS
    @PutMapping("/update-status")
    public String updateStatus(@RequestParam Long id,
                               @RequestParam String status) {
        return adminService.updateStatus(id, status);
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return adminService.getAllUsers();
    }
 // ✅ GET USERS LIST (only USER role)
    @GetMapping("/user")
    public List<User> getUser(){
        return adminService.getAllUser();
    }

    // ✅ GET USER COUNT (IMPORTANT)
    @GetMapping("/users/count")
    public long getUserCount(){
        return adminService.getUserCount();
    }
    @PutMapping("/update/{id}")
    public DeliveryAgent update(@PathVariable Long id,
                                @RequestBody AgentRequest request) {
        return adminService.updateAgent(id, request.getUser(), request.getAgent());
    }
    @DeleteMapping("/agent/delete/{id}")
    public String deleteAgent(@PathVariable Long id) {
        adminService.deleteAgent(id);
        return "Agent deleted successfully";
    }
   
}