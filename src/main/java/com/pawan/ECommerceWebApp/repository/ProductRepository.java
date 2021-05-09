package com.pawan.ECommerceWebApp.repository;

import com.pawan.ECommerceWebApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByCategoryId(int category_id);
}
