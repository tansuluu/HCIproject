package com.example.HCI.controller;

import com.example.HCI.model.Place;
import com.example.HCI.service.PlaceService;
import com.example.HCI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@Transactional
public class MainController {

    @Autowired
    PlaceService placeService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(Model model){
        ArrayList list= (ArrayList)placeService.getTop3PlaceByOrderByView();
        ArrayList list2=userService.getAllByStatus("gid");
        ArrayList list1=userService.getAllByStatus("tourist");
        model.addAttribute("places",list);
        model.addAttribute("gids", list2);
        model.addAttribute("tourist", list1);

        return "index";
    }
}

