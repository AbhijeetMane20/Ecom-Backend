package com.example.ecom.cart;

import com.example.ecom.order.CreateOrderRequest;
import com.example.ecom.order.CustomerOrder;
import com.example.ecom.product.Product;
import com.example.ecom.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    public UserCart addToCart(CartRequest cartRequest, int userId) {
        UserCart userCart = new UserCart();
        Product prod = productRepository.findById(cartRequest.productId).get();
        userCart.product = prod;
        userCart.quantity = cartRequest.quantity;
        userCart.userId = userId;
        return cartRepository.save(userCart);

//        Optional<Product> existingProduct = productRepository.findById(cartRequest.productId);
//        if (existingProduct.isPresent()){
//            Product product = existingProduct.get();
//            userCart.product.add(product);
//            UserCart addProduct = cartRepository.save(userCart);
//            return addProduct;
//        }
//        return null;

    }
}
