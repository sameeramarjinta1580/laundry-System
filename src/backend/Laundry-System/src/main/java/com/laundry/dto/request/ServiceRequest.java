package com.laundry.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class ServiceRequest {
    private String name;
    private String description;
    private Double pricePerItem;
    private MultipartFile image;
    private Long branchId;
    
    
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public ServiceRequest() {
		super();
	}
	public ServiceRequest(String name, String description, Double pricePerItem, MultipartFile image) {
		super();
		this.name = name;
		this.description = description;
		this.pricePerItem = pricePerItem;
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPricePerItem() {
		return pricePerItem;
	}
	public void setPricePerItem(Double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
    
}