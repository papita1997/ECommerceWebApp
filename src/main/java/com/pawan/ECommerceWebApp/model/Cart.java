package com.pawan.ECommerceWebApp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User users;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public Cart() {
    }
}
