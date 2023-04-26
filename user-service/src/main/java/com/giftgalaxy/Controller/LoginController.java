package com.giftgalaxy.Controller;

import com.giftgalaxy.Model.User.Administrator;
import com.giftgalaxy.Model.User.NormalUser;
import com.giftgalaxy.Model.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.giftgalaxy.Database.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    record NewLogin(
            String username,
            String password
    ) {}

    @PostMapping("/login")
    public User login(@RequestBody NewLogin request){
        User user = userRepository.findByUsername(request.username);
        if (user == null || !user.getPassword().equals(request.password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        String type = user.getType();
        Long id = user.getId();
        switch (type) {
            case "Administrator" -> user = new Administrator(user.getUsername(), user.getPassword());
            case "NormalUser" -> user = new NormalUser(user.getUsername(), user.getPassword());
        }

        user.setId(id);

        return user;
    }
}
