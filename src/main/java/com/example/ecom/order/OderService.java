package com.example.ecom.order;

import com.example.ecom.cart.CartItem;
import com.example.ecom.jwtConfig.JwtTokenUtil;
import com.example.ecom.lineItem.LineItem;
import com.example.ecom.lineItem.LineItemRepository;
import com.example.ecom.product.Product;
import com.example.ecom.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    LineItemRepository lineItemRepository;

    public Page<CustomerOrder> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
    @Transactional
    public CustomerOrder addOrder(CreateOrderRequest request, int userId) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.customerName = request.customerName;
        customerOrder.address = request.address;
        customerOrder.userId = userId;
        customerOrder.totalPrice = request.totalPrice;
        CustomerOrder savedOrder = orderRepository.save(customerOrder);
        List<LineItem> dbLineItem = request.lineItems.stream().map(item -> {
            LineItem lineItem = new LineItem();
            lineItem.orderId = savedOrder.orderId;
            lineItem.quantity = item.quantity;
            Product product = productRepository.findByProductId(item.productId);
            lineItem.product = product;
            return lineItem;
        }).collect(Collectors.toList());
        lineItemRepository.saveAll(dbLineItem);


        return savedOrder;
    }


    public void cancleOrder(int id) {
        Optional<CustomerOrder> existingCustomerOrder = orderRepository.findById(id);
        if (existingCustomerOrder.isPresent()) {
            CustomerOrder customerOrder = existingCustomerOrder.get();
            customerOrder.orderStatus = "Cancled";
            orderRepository.save(customerOrder);
        }

    }

    public String getOrderStatus(int id) {
        Optional<CustomerOrder> existingCustomerOrder = orderRepository.findById(id);
        if (existingCustomerOrder.isPresent()) {
            CustomerOrder customerOrder = existingCustomerOrder.get();
            return customerOrder.orderStatus;
        }
        return null;
    }

    public CustomerOrder getOrderById(int id) {
        Optional<CustomerOrder> customerOrder = orderRepository.findById(id);
        if (customerOrder.isPresent()) {
            return customerOrder.get();
        } else {
            return null;
        }
    }

    public List<CustomerOrder> getOrderByUserId(int id) {
        List<CustomerOrder> customerOrder = orderRepository.findByUserId(id);
        return customerOrder;
    }
}
