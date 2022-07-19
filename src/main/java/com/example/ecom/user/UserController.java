package com.example.ecom.user;

import com.example.ecom.jwtConfig.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PutMapping("/signUp")
    public void signUp(@RequestBody User user) throws DuplicateUserException {
         userService.signUp(user);

    }
    @PostMapping("/login")
    public String login(@RequestBody UserRequest request){
        return userService.login(request);
    }
    @GetMapping("/user/{id}")
    public User getUserById (@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public User getUserById(@RequestHeader("Authorization") String authorization){
        String token = authorization.replace("Bearer","");
        int userId = jwtTokenUtil.getUserIdFromToken(token);
        return userService.getUserById(userId);
    }

}
