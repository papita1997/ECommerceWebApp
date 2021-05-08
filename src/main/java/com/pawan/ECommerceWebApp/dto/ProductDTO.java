package com.pawan.ECommerceWebApp.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private long id;
    private String name;
    private double price;
    private double weight;
    private String description;
    private String imageName;
    private int categoryId;
}
