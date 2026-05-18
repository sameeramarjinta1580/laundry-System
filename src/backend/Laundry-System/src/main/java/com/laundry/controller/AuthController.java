package com.laundry.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.laundry.dto.request.LoginRequest;
import com.laundry.dto.request.RegisterRequest;
import com.laundry.dto.response.UserResponse;
import com.laundry.entity.User;
import com.laundry.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
    // 1️⃣ Request OTP
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        return authService.sendOtpEmail(email);
    }

    // 2️⃣ Verify OTP
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam Integer otp) {
        return authService.verifyOtp(email, otp);
    }

    // 3️⃣ Reset password
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        return authService.resetPassword(email, newPassword);
    }
    // Get profile
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam Long userId) {
        Optional<User> user = authService.getProfile(userId);
        if (user.isEmpty()) return ResponseEntity.status(404).body("User not found");
        return ResponseEntity.ok(user.get());
    }

    // Update profile
    @PutMapping("/update-profile")
    public ResponseEntity<String> updateProfile(
            @RequestParam Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String address) {

        String response = authService.updateProfile(userId, name, email, phone, password,address);
        return ResponseEntity.ok(response);
    }
}