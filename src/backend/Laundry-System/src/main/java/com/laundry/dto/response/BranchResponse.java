package com.laundry.dto.response;

public class BranchResponse {

    private Long id;
    private String name;
    private String city;
    private String address;

    private String openingTime;
    private String closingTime;

    private Boolean active;

    // optional: count instead of full list
    private int totalServices;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean Active) {
		this.active = Active;
	}

	public int getTotalServices() {
		return totalServices;
	}

	public void setTotalServices(int totalServices) {
		this.totalServices = totalServices;
	}

    // getters & setters
}