package com.laundry.entity;

import jakarta.persistence.*;

@Entity
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Proper relation with User
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Boolean active = true;

    private String address;
    private String city;

    // ✅ CONSTRUCTOR
    public DeliveryAgent() {}

    // ✅ GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}