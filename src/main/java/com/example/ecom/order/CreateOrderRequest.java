package com.example.ecom.order;

import com.example.ecom.lineItem.LineItem;

import java.util.List;

public class CreateOrderRequest {
    public List<LineItemRequest> lineItems;
    public double totalPrice;
    public String customerName;
    public String address;
}

