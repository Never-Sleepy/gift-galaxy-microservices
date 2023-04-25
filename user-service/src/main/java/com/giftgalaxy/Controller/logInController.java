package com.giftgalaxy.Controller;

import com.giftgalaxy.Model.User.normalUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.giftgalaxy.Database.userRepository;

public class logInController {
    private userRepository userRepository;

    @RequestMapping("/")
    public String checkMVC(){
        return "logIn";
    }

    @RequestMapping("/logIn")
    public String logInHomepage(@RequestParam("userName") String username,
                                @RequestParam("password") String password,
                                Model model){
        normalUser user = null;
        try {
            user = userRepository.findBy(username);
        } catch (Exception e) {
            System.out.println("User not found");
        }
        if (user != null) {
            model.addAttribute("userName",username);
            return "homepage";
        }
        return "logIn";
    }
}
