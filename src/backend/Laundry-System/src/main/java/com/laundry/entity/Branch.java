package com.laundry.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String address;

    private String openingTime;
    private String closingTime;

    @Column(name = "is_active", nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<ServiceEntity> services;

    // ✅ CORRECT GETTER
    public Boolean getActive() {
        return active;
    }

    // ✅ CORRECT SETTER
    public void setActive(Boolean active) {
        this.active = active;
    }

    // OTHER GETTERS/SETTERS

    public Long getId() {
        return id;
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

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }
}