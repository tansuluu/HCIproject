package com.example.HCI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Transactional
public class MainController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}

