package com.example.ecom.user;

import com.example.ecom.order.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserNameAndUserPass(String userName, String pass);
    Optional<User> findByUserName(String userName);


}
