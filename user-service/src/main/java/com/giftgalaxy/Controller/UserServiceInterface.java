package com.giftgalaxy.Controller;

import com.giftgalaxy.Database.UserRepository;
import com.giftgalaxy.Model.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/")
public class UserServiceInterface {
    private final UserRepository userRepository;

    public UserServiceInterface(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    record newInterface(
            Long id
    ) {}

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id){
        User user = userRepository.findById(id);

        return user;
    }
}
