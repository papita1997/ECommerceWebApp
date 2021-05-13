package com.pawan.ECommerceWebApp.service;

import com.pawan.ECommerceWebApp.model.Cart;
import com.pawan.ECommerceWebApp.model.Product;
import com.pawan.ECommerceWebApp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public HashMap<Integer,Product> findAllProductByEmail(String email) {
        HashMap<Integer,Product> products = new HashMap<>();
        cartRepository.findAll().forEach(cart -> {
            if(cart.getUsers().getEmail().equals(email)) {
                products.put(cart.getId(),cart.getProduct());
            }
        });

        return products;
    }

    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteFromCart(int id) {
        cartRepository.deleteById(id);
    }
}
