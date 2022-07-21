package com.example.ecom.product;

import com.example.ecom.cart.UserCart;
import com.example.ecom.order.CustomerOrder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int productId;
    public String productName;
    public String productDescription;
    public double productPrice;
    public String productImage;




}
