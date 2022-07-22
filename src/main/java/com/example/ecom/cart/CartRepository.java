package com.example.ecom.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<UserCart,Integer> {
    List<UserCart> findByUserId(int id);
    @Modifying
    @Query(value = "delete from user_cart cart where cart.user_id=:userId and cart.product_id=:productId", nativeQuery = true)
    void deleteByUserIdAndProductProductId(@Param("userId") int userId,@Param("productId") int productId);
}
