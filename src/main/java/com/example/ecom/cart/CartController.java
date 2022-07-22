package com.example.ecom.cart;

import com.example.ecom.jwtConfig.JwtTokenUtil;
import com.example.ecom.order.CreateOrderRequest;
import com.example.ecom.order.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PutMapping("/addToCart")
    public UserCart addCart(@RequestBody CartRequest cartRequest, @RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer", "");
        int userId = jwtTokenUtil.getUserIdFromToken(token);
        return cartService.addToCart(cartRequest, userId);
    }

    @GetMapping("/userCart")
    public List<UserCart> getUserCartByUserId(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer", "");
        int userId = jwtTokenUtil.getUserIdFromToken(token);
        return cartService.getUserCartByUserId(userId);
    }
    @DeleteMapping("/cartItem/{id}")
    public void deleteItem (@PathVariable int id, @RequestHeader("Authorization") String authorization){
        String token = authorization.replace("Bearer", "");
        int userId = jwtTokenUtil.getUserIdFromToken(token);
        cartService.deleteUserItemByUserId(id,userId);
    }

}