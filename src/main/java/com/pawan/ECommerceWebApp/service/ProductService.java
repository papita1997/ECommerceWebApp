package com.pawan.ECommerceWebApp.service;

import com.pawan.ECommerceWebApp.model.Product;
import com.pawan.ECommerceWebApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> findProductById(long id) {
        return productRepository.findById(id);
    }

    public void removeProductById(long id) {
        productRepository.deleteById(id);
    }
}
