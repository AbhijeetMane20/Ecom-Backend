package com.example.ecom.cart;

import com.example.ecom.product.Product;

import javax.persistence.*;

@Entity
public class UserCart {
    public int userId;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    public Product product;
}
