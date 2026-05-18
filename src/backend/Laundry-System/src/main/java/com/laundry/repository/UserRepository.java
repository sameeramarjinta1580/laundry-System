package com.laundry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // 👉 Login
    Optional<User> findByEmailAndPassword(String email, String password);

    // 👉 Check duplicate email during registration
    boolean existsByEmail(String email);

    // 👉 Get user by email (useful for profile)
    Optional<User> findByEmail(String email);
    long count();
    List<User> findAll();
    List<User> findByRole(String role);

    long countByRole(String role);
}