package com.example.ecom.order;

import com.example.ecom.product.Product;
import com.example.ecom.user.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int orderId;

    public int quantity;
    //public double totalPrice = product.price * quantity;
    public double totalPrice;
    public double totalPriceWithGST;
    public String address;
    public String customerName;
    public String orderStatus = "Order Placed";
    public int userId;
//    public int productId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    public Product product;

//    @OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "userId")
//    public User user;




//    @OneToMany (mappedBy = "customerOrder")
//    public List<Product> products;


}
