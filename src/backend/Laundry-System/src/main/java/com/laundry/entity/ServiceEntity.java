package com.laundry.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double pricePerItem;

    private String imageUrl; // stored filename

    @ManyToOne
    @JoinColumn(name = "branch_id") // ⭐ NEW
    @JsonIgnore
    private Branch branch;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public ServiceEntity() {
		super();
	}

	public ServiceEntity(String name, String description, Double pricePerItem, String imageUrl) {
		super();
		this.name = name;
		this.description = description;
		this.pricePerItem = pricePerItem;
		this.imageUrl = imageUrl;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}
    
}
