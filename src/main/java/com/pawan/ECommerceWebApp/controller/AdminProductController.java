package com.pawan.ECommerceWebApp.controller;

import com.pawan.ECommerceWebApp.dto.ProductDTO;
import com.pawan.ECommerceWebApp.model.Product;
import com.pawan.ECommerceWebApp.service.CategoryService;
import com.pawan.ECommerceWebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Controller
public class AdminProductController {
    public final String IMAGE_UNIQUE_ID = "id="+ UUID.randomUUID();
    public final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin/products/add")
    public String addProductGet(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String addProductPost(@ModelAttribute("productDTO")ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        product.setCategory(categoryService.findCategoryById(productDTO.getCategoryId()).get());
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = IMAGE_UNIQUE_ID+file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        Optional<Product> product = productService.findProductById(id);
        if(product.isPresent()){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setDescription(product.get().getDescription());
            productDTO.setPrice(product.get().getPrice());
            productDTO.setName(product.get().getName());
            productDTO.setWeight(product.get().getWeight());
            productDTO.setImageName(product.get().getImageName());
            productDTO.setCategoryId(product.get().getCategory().getId());
            productDTO.setId(product.get().getId());
            model.addAttribute("productDTO",productDTO);
            model.addAttribute("categories", categoryService.findAllCategories());
        }
        return "productsAdd";
    }

}
