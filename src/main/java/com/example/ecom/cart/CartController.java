package com.example.ecom.cart;

import com.example.ecom.jwtConfig.JwtTokenUtil;
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
    public CartItem addCart(@RequestBody CartRequest cartRequest, @RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer", "");
        int userId = jwtTokenUtil.getUserIdFromToken(token);
        return cartService.addToCart(cartRequest, userId);
    }

    @PostMapping("/updateCart/{id}")
    public void updateCart(@PathVariable int id, @RequestBody CartRequest cartRequest) {
        cartService.updateCart(id, cartRequest);
    }

    @GetMapping("/userCart")
    public CartResponse getUserCartByUserId(@RequestHeader("Authorization") String authorization) {
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
    @DeleteMapping("/cartItem")
    public void deleteCart(){
        cartService.deleteUserCart();
    }

}