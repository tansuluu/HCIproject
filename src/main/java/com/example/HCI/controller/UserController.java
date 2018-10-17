package com.example.HCI.controller;

import com.example.HCI.model.User;
import com.example.HCI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/userPage")
    public String showUser(Model model, @RequestParam("username")String email){
        User user=userService.findUserByEmail(email);
        model.addAttribute("user",user);
        return "profile";
    }
}
