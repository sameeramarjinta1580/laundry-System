package com.laundry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {

    List<DeliveryAgent> findByActiveTrue();
    DeliveryAgent findByUserId(Long userId);
}