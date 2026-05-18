package com.laundry.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

    private String role; // USER / ADMIN / AGENT
    private Integer otp;
    private Long otpGeneratedTime;
    

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public Long getOtpGeneratedTime() {
		return otpGeneratedTime;
	}

	public void setOtpGeneratedTime(Long otpGeneratedTime) {
		this.otpGeneratedTime = otpGeneratedTime;
	}

	public User() {
		super();
	}

	public User(String name, String email, String password, String phone, String address, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}
    
}
