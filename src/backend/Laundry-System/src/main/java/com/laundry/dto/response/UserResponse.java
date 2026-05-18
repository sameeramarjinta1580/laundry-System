package com.laundry.dto.response;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String address;
    private String phone;
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UserResponse() {
		super();
	}
	public UserResponse(Long id, String name, String email,String address,String phone, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address=address;
		this.phone=phone;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
}
