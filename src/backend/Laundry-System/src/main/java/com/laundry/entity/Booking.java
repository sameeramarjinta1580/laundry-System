package com.laundry.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "branch_id")
    private Long branchId;     // ⭐ NEW
    private String orderId; // ⭐ for Cashfree// ⭐ NEW
    @Column(name = "user_id")
    private Long userId;
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
	@Column(name = "service_id")
	private Long serviceId;

    private int shirtCount;
    private int pantCount;
 // ADD THIS FIELD
    @Column(name = "other_items")
    private String otherItems;

    // ADD GETTER SETTER
    public String getOtherItems() {
        return otherItems;
    }

    public void setOtherItems(String otherItems) {
        this.otherItems = otherItems;
    }

    private Double totalPrice;

    private String deliveryType;   // SELF / HOME
    private String paymentType;    // COD / ONLINE
    private String paymentStatus;  // PENDING / PAID

    private String orderStatus;    // PLACED / IN_PROGRESS / COMPLETED / DELIVERED

    private String address;
    private int deliveryDays;

    private LocalDateTime bookingDate;
    @Column(name = "delivery_agent_id")
    private Long deliveryAgentId; // link agent

    private LocalDateTime pickupTime;
    private LocalDateTime deliveryTime;

	public Long getDeliveryAgentId() {
		return deliveryAgentId;
	}

	public void setDeliveryAgentId(Long deliveryAgentId) {
		this.deliveryAgentId = deliveryAgentId;
	}

	public LocalDateTime getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(LocalDateTime pickupTime) {
		this.pickupTime = pickupTime;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "service_id", insertable = false, updatable = false)
	private ServiceEntity service;

	@ManyToOne
	@JoinColumn(name = "branch_id", insertable = false, updatable = false)
	private Branch branch;

	@ManyToOne
	@JoinColumn(name = "delivery_agent_id", insertable = false, updatable = false)
	private DeliveryAgent agent;
   
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ServiceEntity getService() {
		return service;
	}

	public void setService(ServiceEntity service) {
		this.service = service;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public DeliveryAgent getAgent() {
		return agent;
	}

	public void setAgent(DeliveryAgent agent) {
		this.agent = agent;
	}

	public Booking() {
		super();
	}

	public Booking(Long userId, Long serviceId, int shirtCount, int pantCount, Double totalPrice, String deliveryType,
			String paymentType, String paymentStatus, String orderStatus, String address, int deliveryDays,
			LocalDateTime bookingDate) {
		super();
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

	public Long getId() {
		return id;
	}
    
}