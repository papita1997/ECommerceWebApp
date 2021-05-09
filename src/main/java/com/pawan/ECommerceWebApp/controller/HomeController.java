package com.pawan.ECommerceWebApp.controller;

import com.pawan.ECommerceWebApp.service.CategoryService;
import com.pawan.ECommerceWebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/","/home"})
    public String homePage() {
        return "index";
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("products", productService.findAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String showProductByCategory(@PathVariable int id, Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("products", productService.findAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewSingleProductDetails(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findProductById(id).get());
        return "viewProduct";
    }
}
