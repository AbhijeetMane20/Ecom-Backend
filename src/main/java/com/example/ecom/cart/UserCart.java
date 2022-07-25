package com.example.ecom.cart;

import com.example.ecom.product.Product;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserCart {
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
