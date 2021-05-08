package com.pawan.ECommerceWebApp.controller;

import com.pawan.ECommerceWebApp.model.Category;
import com.pawan.ECommerceWebApp.service.CategoryService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminCategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin/categories/add")
    public String categoryAddGet(Model model) {
        model.addAttribute("category" , new Category());
        model.addAttribute("title" ,"E-Commerce Add Category");
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String categoryAddPost(@ModelAttribute("category") Category category, Model model) {
        categoryService.addCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String categoryDeleteGet(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String categoryUpdateGet(@PathVariable int id , Model model) {
        Optional<Category> category = categoryService.findCategoryById(id);
        if(category.isPresent()) {
            model.addAttribute("category" , category.get());
        }
        model.addAttribute("title" ,"E-Commerce Update Category");
        return "categoriesAdd";
    }

}
