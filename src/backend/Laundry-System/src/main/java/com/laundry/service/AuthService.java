package com.laundry.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.dto.request.LoginRequest;
import com.laundry.dto.request.RegisterRequest;
import com.laundry.dto.response.UserResponse;
import com.laundry.entity.User;
import com.laundry.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private EmailService emailservice;

    // ✅ REGISTER
    public String register(RegisterRequest req) {

        if (userRepo.existsByEmail(req.getEmail())) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setAddress(req.getAddress());
        user.setPhone(req.getPhone());
        user.setRole("USER"); // 👈 default role

        userRepo.save(user);

        return "User Registered Successfully";
    }

    // ✅ LOGIN
    public UserResponse login(LoginRequest req) {

        Optional<User> userOpt =
                userRepo.findByEmailAndPassword(req.getEmail(), req.getPassword());

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid Credentials");
        }

        User user = userOpt.get();

        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setAddress(user.getAddress());
        res.setPhone(user.getPhone());
        res.setRole(user.getRole());

        return res;
    }
    // 1️⃣ Send OTP
    public String sendOtpEmail(String email) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return "User not found";

        User user = userOpt.get();
        int otp = generateOtp();
        user.setOtp(otp);
        user.setOtpGeneratedTime(System.currentTimeMillis());
        userRepo.save(user);

        emailservice.sendOtp(email, otp, user.getName());
        return "OTP sent successfully to your email";
    }

    // 2️⃣ Verify OTP
    public String verifyOtp(String email, Integer otp) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return "User not found";

        User user = userOpt.get();

        // Check expiry: 5 min = 300000 ms
        if (System.currentTimeMillis() - user.getOtpGeneratedTime() > 300000) {
            return "OTP expired, please request again";
        }

        if (!user.getOtp().equals(otp)) return "Invalid OTP";

        return "OTP verified successfully";
    }

    // 3️⃣ Reset Password
    public String resetPassword(String email, String newPassword) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return "User not found";

        User user = userOpt.get();
        user.setPassword(newPassword);
        user.setOtp(null); // clear OTP
        user.setOtpGeneratedTime(null);
        userRepo.save(user);

        return "Password updated successfully";
    }

    private int generateOtp() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
    public String updateProfile(Long userId, String name, String email, String phone, String password,String address) {
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) return "User not found";

        User user = userOpt.get();

        if (name != null && !name.isEmpty()) user.setName(name);
        if (email != null && !email.isEmpty()) user.setEmail(email);
        if (phone != null && !phone.isEmpty()) user.setPhone(phone);
        if (password != null && !password.isEmpty()) user.setPassword(password);
        if(address !=null && !address.isEmpty()) user.setAddress(address);

        userRepo.save(user);

        return "Profile updated successfully";
    }

    public Optional<User> getProfile(Long userId) {
        return userRepo.findById(userId);
    }
    
}