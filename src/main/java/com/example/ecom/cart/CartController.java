package com.example.ecom.cart;

import com.example.ecom.jwtConfig.JwtTokenUtil;
import com.example.ecom.order.CreateOrderRequest;
import com.example.ecom.order.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PutMapping("/addToCart")
    public UserCart addCart (@RequestBody CartRequest cartRequest, @RequestHeader("Authorization") String authorization){
        String token = authorization.replace("Bearer","");
        int userId = jwtTokenUtil.getUserIdFromToken(token);
        return cartService.addToCart(cartRequest, userId);
    }
}

