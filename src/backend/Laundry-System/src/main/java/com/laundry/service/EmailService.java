package com.laundry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class  EmailService {

    @Autowired
    private JavaMailSender mailSender;

    
    public void sendOtp(String toEmail, int otp, String userName) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);

            helper.setTo(toEmail);
            helper.setSubject("Password Reset OTP - Laundry System");

            String html = "<div style='font-family:Arial;'>"
                    + "<h2>OTP Verification Code</h2>"
                    + "<p>Dear " + userName + ",</p>"
                    + "<p>Your OTP for password reset is:</p>"
                    + "<h1 style='color:blue;'>" + otp + "</h1>"
                    + "<p>This OTP will expire in 5 minutes.</p>"
                    + "<p>If you didn't request this, ignore this email.</p>"
                    + "</div>";

            helper.setText(html, true);
            mailSender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
