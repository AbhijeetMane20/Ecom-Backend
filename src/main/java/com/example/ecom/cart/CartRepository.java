package com.example.ecom.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByUserId(int id);
    @Modifying
    @Query(value = "delete from user_cart cart where cart.user_id=:userId and cart.product_id=:productId", nativeQuery = true)
    void deleteByUserIdAndProductProductId(@Param("userId") int userId,@Param("productId") int productId);

    @Query(value = "SELECT sum(total_price) from user_cart cart where cart.user_id=:userId", nativeQuery = true)
    double getUserCartTotalPrice(@Param("userId") int userId);

    @Modifying
    @Query(value = "update user_cart cart set cart.quantity=:quantity,cart.total_price=:totalPrice,cart.total_price_withgst=:totalPriceWithGST where cart.cart_item_id=:cartItemId", nativeQuery = true)
    void updateByCartItemId(@Param("quantity") int quantity, @Param("totalPrice") double totalPrice, @Param("totalPriceWithGST") double totalPriceWithGST ,@Param("cartItemId") int cartItemId);
}
