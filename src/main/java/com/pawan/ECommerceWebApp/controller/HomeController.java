package com.pawan.ECommerceWebApp.controller;

import com.pawan.ECommerceWebApp.service.CartService;
import com.pawan.ECommerceWebApp.service.CategoryService;
import com.pawan.ECommerceWebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @GetMapping({"/","/home"})
    public String homePage(Model model , Principal principal) {
        model.addAttribute("cartCount",cartService.findAllProductByEmail(principal==null?"":principal.getName()).size());
        return "index";
    }

    @GetMapping("/shop")
    public String shopPage(Model model, Principal principal) {
        model.addAttribute("cartCount",cartService.findAllProductByEmail(principal==null?"":principal.getName()).size());
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("products", productService.findAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String showProductByCategory(@PathVariable int id, Model model, Principal principal) {
        model.addAttribute("cartCount",cartService.findAllProductByEmail(principal==null?"":principal.getName()).size());
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("products", productService.findAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewSingleProductDetails(@PathVariable long id, Model model, Principal principal) {
        model.addAttribute("cartCount",cartService.findAllProductByEmail(principal==null?"":principal.getName()).size());
        model.addAttribute("product", productService.findProductById(id).get());
        return "viewProduct";
    }
}
