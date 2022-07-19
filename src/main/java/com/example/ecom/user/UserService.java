package com.example.ecom.user;

import com.example.ecom.jwtConfig.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void signUp(User user) throws DuplicateUserException {
        Optional<User> existingUser = userRepository.findByUserName(user.userName);
        if (existingUser.isPresent()){
           throw new DuplicateUserException("User already exists");
//            return existingUser.get();
        }
        else {
            userRepository.save(user);
        }

    }

    public String login(UserRequest request) {

        Optional<User> existingUser = userRepository.findByUserNameAndUserPass(request.userName, request.userPass);
        if (existingUser.isPresent()) {
            String token =jwtTokenUtil.generateToken(existingUser.get());
            return token;
        } else return null;


    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        else {
            return null;
        }
    }
}
