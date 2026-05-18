package com.laundry.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.dto.response.AdminDashboardResponse;
import com.laundry.dto.response.BookingResponse;
import com.laundry.entity.Booking;
import com.laundry.entity.DeliveryAgent;
import com.laundry.entity.User;
import com.laundry.repository.BookingRepository;
import com.laundry.repository.BranchRepo;
import com.laundry.repository.DeliveryAgentRepository;
import com.laundry.repository.ServiceRepository;
import com.laundry.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ServiceRepository serviceRepo;
    @Autowired
    private BranchRepo brepo;
    @Autowired
    private DeliveryAgentRepository agentRepo;
  

    // ✅ DASHBOARD STATS
    public AdminDashboardResponse getDashboardStats() {

        AdminDashboardResponse res = new AdminDashboardResponse();

        res.setTotalUsers(userRepo.countByRole("USER"));
        res.setTotalServices(serviceRepo.count());
        res.setTotalBookings(bookingRepo.count());
        res.setTotalBranches(brepo.count());
        return res;
    }
    // ✅ VIEW ALL BOOKINGS
    public List<BookingResponse> getAllBookings() {
        return bookingRepo.findAll()
                .stream()
                .map(bookingService::mapToResponse)
                .collect(Collectors.toList());
    }
    
    // ✅ UPDATE STATUS
    public String updateStatus(Long id, String status) {

        Booking booking = bookingRepo.findById(id).orElseThrow();

        booking.setOrderStatus(status);

        // 👇 If completed and COD → mark paid
        if (status.equalsIgnoreCase("DELIVERED") &&
            booking.getPaymentType().equalsIgnoreCase("COD")) {

            booking.setPaymentStatus("PAID");
        }

        bookingRepo.save(booking);

        return "Status Updated";
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
 // ✅ ONLY USERS LIST
    public List<User> getAllUser() {
        return userRepo.findByRole("USER");
    }

    // ✅ COUNT ONLY USERS
    public long getUserCount() {
        return userRepo.countByRole("USER");
    }
    public DeliveryAgent updateAgent(Long id, User userReq, DeliveryAgent agentReq) {

        DeliveryAgent agent = agentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        User user = agent.getUser();

        // 🔥 Update USER
        user.setName(userReq.getName());
        user.setEmail(userReq.getEmail());
        user.setPhone(userReq.getPhone());

        userRepo.save(user);

        // 🔥 Update AGENT
        agent.setAddress(agentReq.getAddress());
        agent.setCity(agentReq.getCity());
        agent.setActive(agentReq.getActive());

        return agentRepo.save(agent);
    }
    public void deleteAgent(Long id) {

        DeliveryAgent agent = agentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        User user = agent.getUser();

        agentRepo.delete(agent);
        userRepo.delete(user); // 🔥 remove login also
    }
}