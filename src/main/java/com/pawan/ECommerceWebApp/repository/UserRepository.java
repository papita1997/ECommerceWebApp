package com.pawan.ECommerceWebApp.repository;

import com.pawan.ECommerceWebApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByEmail(String email);

}
