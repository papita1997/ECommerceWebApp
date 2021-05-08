package com.pawan.ECommerceWebApp.repository;

import com.pawan.ECommerceWebApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
