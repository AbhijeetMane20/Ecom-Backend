package com.example.ecom.cart;

import com.example.ecom.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "user_cart")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int cartItemId;
    public int userId;
    public int quantity;

    public double totalPrice;
    public double totalPriceWithGST;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    public Product product;
}
