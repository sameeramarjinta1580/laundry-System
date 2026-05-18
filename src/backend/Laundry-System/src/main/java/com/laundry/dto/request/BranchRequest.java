package com.laundry.dto.request;

public class BranchRequest {

    private String name;
    private String city;
    private String address;

    private String openingTime;
    private String closingTime;

    private Boolean active;

    // ✅ CORRECT GETTER
    public Boolean getActive() {
        return active;
    }

    // ✅ CORRECT SETTER
    public void setActive(Boolean active) {
        this.active = active;
    }

    // OTHER GETTERS/SETTERS

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
}