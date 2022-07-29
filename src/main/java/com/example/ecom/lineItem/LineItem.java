package com.example.ecom.lineItem;

import com.example.ecom.order.CustomerOrder;
import com.example.ecom.product.Product;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int orderId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    public Product product;
    public int quantity;

}
