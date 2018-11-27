package com.example.HCI.controller;

import com.example.HCI.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
@Transactional
public class MainController {

    @Autowired
    PlaceService placeService;

    @RequestMapping("/")
    public String index(Model model){
        ArrayList list= (ArrayList)placeService.getTop3PlaceByOrderByView();
        model.addAttribute("places",list);
        return "index";
    }
}

