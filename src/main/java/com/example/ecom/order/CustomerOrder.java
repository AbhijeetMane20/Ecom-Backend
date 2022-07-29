package com.example.ecom.order;

import com.example.ecom.lineItem.LineItem;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int orderId;

    public double totalPrice;
    public double totalPriceWithGST;
    public String address;
    public String customerName;
    public String orderStatus = "Order Placed";
    public int userId;






//    @OneToMany (mappedBy = "customerOrder")
//    public List<Product> products;


}
