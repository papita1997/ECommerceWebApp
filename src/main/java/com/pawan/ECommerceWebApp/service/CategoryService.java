package com.pawan.ECommerceWebApp.service;

import com.pawan.ECommerceWebApp.model.Category;
import com.pawan.ECommerceWebApp.repository.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRespository categoryRespository;

    public List<Category> findAllCategories() {
        return categoryRespository.findAll();
    }

    public void addCategory(Category category) {
        categoryRespository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRespository.deleteById(id);
    }

    public Optional<Category> findCategoryById(int id) {
        return categoryRespository.findById(id);
    }
}
