package com.pawan.ECommerceWebApp.controller;

import com.pawan.ECommerceWebApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin")
    public String adminHomePage() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String adminCategoryPage(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }
}
