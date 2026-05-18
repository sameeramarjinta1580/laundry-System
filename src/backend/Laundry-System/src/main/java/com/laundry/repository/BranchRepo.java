package com.laundry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.entity.Branch;

public interface BranchRepo extends JpaRepository<Branch, Long> {

    List<Branch> findByCity(String city);

    List<Branch> findByActiveTrue();

    List<Branch> findByCityAndActiveTrue(String city);
    long count();
}