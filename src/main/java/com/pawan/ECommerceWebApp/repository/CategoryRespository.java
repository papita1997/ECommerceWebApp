package com.pawan.ECommerceWebApp.repository;

import com.pawan.ECommerceWebApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends JpaRepository<Category,Integer> {
}
