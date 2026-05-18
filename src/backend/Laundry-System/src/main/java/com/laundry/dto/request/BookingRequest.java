package com.laundry.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookingRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Service ID is required")
    private Long serviceId;

    @NotNull(message = "Branch ID is required")
    private Long branchId;

    @Min(value = 0, message = "Shirt count cannot be negative")
    private int shirtCount;

    @Min(value = 0, message = "Pant count cannot be negative")
    private int pantCount;

    @NotBlank(message = "Delivery type is required")
    private String deliveryType;

    @NotBlank(message = "Payment type is required")
    private String paymentType;

    @NotBlank(message = "Address is required")
    private String address;

    private String otherItems;

    // GETTERS & SETTERS

    public String getOtherItems() {
        return otherItems;
    }

    public void setOtherItems(String otherItems) {
        this.otherItems = otherItems;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public BookingRequest() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public int getShirtCount() {
        return shirtCount;
    }

    public void setShirtCount(int shirtCount) {
        this.shirtCount = shirtCount;
    }

    public int getPantCount() {
        return pantCount;
    }

    public void setPantCount(int pantCount) {
        this.pantCount = pantCount;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}