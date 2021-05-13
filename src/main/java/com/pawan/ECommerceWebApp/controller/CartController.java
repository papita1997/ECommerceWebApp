package com.pawan.ECommerceWebApp.controller;

import com.pawan.ECommerceWebApp.model.Cart;
import com.pawan.ECommerceWebApp.model.Product;
import com.pawan.ECommerceWebApp.repository.UserRepository;
import com.pawan.ECommerceWebApp.service.CartService;
import com.pawan.ECommerceWebApp.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Log4j2
@Controller
public class CartController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public String cartPage(Model model, Principal principal) {
        HashMap<Integer,Product> products = cartService.findAllProductByEmail(principal.getName());
        model.addAttribute("cart",products);
        model.addAttribute("cartCount",products.size());
        model.addAttribute("total",products.keySet().stream().mapToDouble(keys -> products.get(keys).getPrice()).sum());
        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable long id, Principal principal){
        log.warn("email "+principal.getName());
        HashMap<Integer,Product> cartProducts = cartService.findAllProductByEmail(principal.getName());
            AtomicBoolean itemPresent = new AtomicBoolean(false);
            cartProducts.keySet().forEach(key -> {
                if (cartProducts.get(key).getId()==id) {
                   itemPresent.set(true);
                }
            });

            if (!itemPresent.get()) {
                Cart cart = new Cart();
                cart.setProduct(productService.findProductById(id).get());
                cart.setUsers(userRepository.findUserByEmail(principal.getName()).get());
                cartService.addToCart(cart);
            }

        return "redirect:/cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String removeFromCart(@PathVariable int index) {
        cartService.deleteFromCart(index);
        return "redirect:/cart";
    }
}
