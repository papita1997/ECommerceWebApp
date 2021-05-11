package com.pawan.ECommerceWebApp.repository;

import com.pawan.ECommerceWebApp.model.Cart;
import com.pawan.ECommerceWebApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
