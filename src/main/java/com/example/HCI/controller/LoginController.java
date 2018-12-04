package com.example.HCI.controller;


import com.example.HCI.model.User;
import com.example.HCI.service.StorageService;
import com.example.HCI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@Transactional
@Controller
public class LoginController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult,@RequestParam(name = "file",required = false)MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("hello");
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "*There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            user.setImage("member.png");
            if (file!=null && !file.isEmpty()){
                storageService.saveAvatar(file);
                user.setImage(file.getOriginalFilename());
            }
            if (user.getCountry()==null || user.getCountry().equalsIgnoreCase("")) {
                user.setCountry("Kyrgyzstan");
                user.setStatus("gid");
                userService.saveUser(user, "GID");
                modelAndView.addObject("successMessage", "User has been registered successfully as GID");
            }
            else {
                user.setStatus("tourist");
                userService.saveUser(user, "TOURIST");
                modelAndView.addObject("successMessage", "User has been registered successfully as Tourist");
            }

            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " "  + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;

    }
}

