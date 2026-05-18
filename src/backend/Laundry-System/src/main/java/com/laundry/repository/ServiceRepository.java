package com.laundry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    long count(); // already present (optional, JpaRepository already has it)

    // ⭐ Get all services for a specific branch
    List<ServiceEntity> findByBranchId(Long branchId);

    // ⭐ Get all services in a city (via branch)
    List<ServiceEntity> findByBranchCity(String city);

    // ⭐ Get services only from active branches
    List<ServiceEntity> findByBranchCityAndBranchActiveTrue(String city);
}