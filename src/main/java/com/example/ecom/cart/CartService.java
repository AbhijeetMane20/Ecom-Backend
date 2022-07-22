package com.example.ecom.cart;

import com.example.ecom.product.Product;
import com.example.ecom.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    }

    public List<UserCart> getUserCartByUserId(int id) {
        List<UserCart> userCarts = cartRepository.findByUserId(id);
        return userCarts;
    }

    @Transactional
    public void deleteUserItemByUserId(int id, int userId) {

            cartRepository.deleteByUserIdAndProductProductId(userId,id);

    }
}