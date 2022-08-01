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


    public CartItem addToCart(CartRequest cartRequest, int userId) {

            CartItem cartItem = new CartItem();
            Product prod = productRepository.findById(cartRequest.productId).get();
            cartItem.product = prod;
            cartItem.quantity = cartRequest.quantity;
            cartItem.userId = userId;


            Optional<Product> existingProduct = productRepository.findById(cartRequest.productId);
            if (existingProduct.isPresent()) {
                Product product = existingProduct.get();
                cartItem.totalPrice = cartRequest.quantity * product.productPrice;
                cartItem.totalPriceWithGST = cartItem.totalPrice * 1.18;
                CartItem savedOrder = cartRepository.save(cartItem);
                return savedOrder;
            }

return null;
    }

    public CartResponse getUserCartByUserId(int id) {
        List<CartItem> cartItems = cartRepository.findByUserId(id);
        CartResponse cartResponse = new CartResponse();
        cartResponse.cartItems = cartItems;
        cartResponse.totalPrice = cartRepository.getUserCartTotalPrice(id);
        return cartResponse;
    }

    @Transactional
    public void deleteUserItemByUserId(int id, int userId) {

            cartRepository.deleteByUserIdAndProductProductId(userId,id);

    }

    @Transactional
    public void updateCart(int id, CartRequest cartRequest) {

        Optional<Product> existingProduct = productRepository.findById(cartRequest.productId);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
           double totalPrice = cartRequest.quantity * product.productPrice;
            double totalPriceWithGST = totalPrice * 1.18;

            cartRepository.updateByCartItemId(cartRequest.quantity, totalPrice,totalPriceWithGST,id);

        }
    }

    public void deleteUserCart() {
        cartRepository.deleteAll();
    }
}