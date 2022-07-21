package com.example.ecom.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<UserCart,Integer> {
    List<UserCart> findByUserId(int id);
}
