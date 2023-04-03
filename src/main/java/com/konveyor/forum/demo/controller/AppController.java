package com.konveyor.forum.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.konveyor.forum.demo.service.UserService;
import com.konveyor.forum.demo.model.User;
import com.konveyor.forum.demo.model.Role;


@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping(value={"/", "/login"})
    public String login(Model model){
return "login";
    }


    @GetMapping(value="/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String createNewUser(User user, BindingResult bindingResult,Model model) {

        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("user", new User());
        }
        return "login";
    }




}