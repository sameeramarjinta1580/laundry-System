package com.laundry.dto.response;

import java.time.LocalDateTime;

public class BookingResponse {

    private Long id;
    private Long userId;
    private Long serviceId;

    private int shirtCount;
    private int pantCount;
 // ADD THIS FIELD
    private String otherItems;

    // GETTER SETTER
    public String getOtherItems() {
        return otherItems;
    }

    public void setOtherItems(String otherItems) {
        this.otherItems = otherItems;
    }
    private Double totalPrice;

    private String deliveryType;
    private String paymentType;
    private String paymentStatus;

    private String orderStatus;

    private String address;
    private int deliveryDays;
    private Long branchId;
    private String orderId;
    private String userName;
    private String userPhone;

    private String serviceName;

    private String branchName;

    private String agentName;
    private String agentPhone;

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BookingResponse() {
		super();
	}

	public BookingResponse(Long id, Long userId, Long serviceId, int shirtCount, int pantCount, Double totalPrice,
			String deliveryType, String paymentType, String paymentStatus, String orderStatus, String address,
			int deliveryDays, LocalDateTime bookingDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.serviceId = serviceId;
		this.shirtCount = shirtCount;
		this.pantCount = pantCount;
		this.totalPrice = totalPrice;
		this.deliveryType = deliveryType;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.orderStatus = orderStatus;
		this.address = address;
		this.deliveryDays = deliveryDays;
		this.bookingDate = bookingDate;
	}

	private LocalDateTime bookingDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDeliveryDays() {
		return deliveryDays;
	}

	public void setDeliveryDays(int deliveryDays) {
		this.deliveryDays = deliveryDays;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

    // getters & setters
}