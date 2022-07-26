package com.example.ecom.product;

import lombok.NoArgsConstructor;

import javax.persistence.*;

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
