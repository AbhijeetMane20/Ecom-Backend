package com.example.ecom.order;

import com.example.ecom.jwtConfig.JwtTokenUtil;
import com.example.ecom.product.Product;
import com.example.ecom.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    public Page<CustomerOrder> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public CustomerOrder addOrder(CreateOrderRequest request, int userId) {
        CustomerOrder customerOrder = new CustomerOrder();
        Product prod = productRepository.findById(request.productId).get();
//        prod.productId = request.productId;
        customerOrder.product = prod;
        customerOrder.customerName = request.customerName;
        customerOrder.address = request.address;
        customerOrder.quantity = request.quantity;
        customerOrder.userId = userId;


        Optional<Product> existingProduct = productRepository.findById(request.productId);
        if (existingProduct.isPresent()){
            Product product = existingProduct.get();
            customerOrder.totalPrice = request.quantity * product.productPrice;
            customerOrder.totalPriceWithGST = customerOrder.totalPrice * 1.18;
            CustomerOrder savedOrder = orderRepository.save(customerOrder);
            return savedOrder;
        }
        return null;
    }



    public void cancleOrder(int id) {
        Optional<CustomerOrder> existingCustomerOrder = orderRepository.findById(id);
        if (existingCustomerOrder.isPresent()){
            CustomerOrder customerOrder = existingCustomerOrder.get();
            customerOrder.orderStatus = "Cancled";
            orderRepository.save(customerOrder);
        }

    }

    public String getOrderStatus(int id) {
        Optional<CustomerOrder> existingCustomerOrder = orderRepository.findById(id);
        if (existingCustomerOrder.isPresent()){
            CustomerOrder customerOrder = existingCustomerOrder.get();
            return customerOrder.orderStatus;
        }
        return null;
    }
    public CustomerOrder getOrderById(int id) {
        Optional<CustomerOrder> customerOrder = orderRepository.findById(id);
        if (customerOrder.isPresent()){
            return customerOrder.get();
        }
        else {
            return null;
        }
    }

    public List<CustomerOrder> getOrderByUserId(int id) {
        List<CustomerOrder> customerOrder = orderRepository.findByUserId(id);
        return customerOrder;
    }
}
