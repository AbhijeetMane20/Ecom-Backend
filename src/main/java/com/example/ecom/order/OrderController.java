package com.example.ecom.order;

import com.example.ecom.jwtConfig.JwtTokenUtil;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController("/order")
public class OrderController {
    @Autowired
    OderService oderService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/orders")
    public Page<CustomerOrder> getAllOrders(Pageable pageable){
        return oderService.getAllOrders(pageable);
    }

    @PutMapping("/order")
    public CustomerOrder addOrder (@RequestBody CreateOrderRequest request, @RequestHeader("Authorization") String authorization){
        String token = authorization.replace("Bearer","");
        int userId = jwtTokenUtil.getUserIdFromToken(token);
        return oderService.addOrder(request, userId);
    }
    @PostMapping("/order/{id}/cancle")
    public void cancleOrder (@PathVariable int id){

        oderService.cancleOrder(id);

    }
    @GetMapping ("/order/{id}/status")
    public String getStatus (@PathVariable int id){
        return oderService.getOrderStatus(id);
    }
    @GetMapping ("/order/{id}")
    public CustomerOrder getOrderById (@PathVariable int id){
        return oderService.getOrderById(id);
    }
    @GetMapping ("/order")
    public List<CustomerOrder> getOrderByUserId (@RequestHeader("Authorization") String authorization){
        String token = authorization.replace("Bearer","");
        int userId = jwtTokenUtil.getUserIdFromToken(token);
        return oderService.getOrderByUserId(userId);
    }

}
