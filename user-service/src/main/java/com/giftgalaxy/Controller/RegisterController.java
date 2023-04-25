package com.giftgalaxy.Controller;

import com.giftgalaxy.Database.UserRepository;
import com.giftgalaxy.Model.User.Administrator;
import com.giftgalaxy.Model.User.NormalUser;
import com.giftgalaxy.Model.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {

    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    record NewRegister(
            String username,
            String password,
            String type
    ) {}

    @PostMapping("/register")
    public User register(@RequestBody NewRegister request) {
        // Check if user already exists
        User existingUser = userRepository.findByUsername(request.username);
        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        // Create new user
        User newUser = switch (request.type) {
            case "Administrator" -> new User(request.username, request.password,"Administrator");
            case "NormalUser" -> new User(request.username, request.password, "NormalUser");
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user type");
        };

        // Save new user to database
        userRepository.save(newUser);

        return newUser;
    }
}
