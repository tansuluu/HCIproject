package com.example.HCI.controller;


import com.example.HCI.model.Comment;
import com.example.HCI.model.Likes;
import com.example.HCI.model.Place;
import com.example.HCI.repository.PlaceRepositoty;
import com.example.HCI.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Transactional
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @Autowired
    StorageService storageService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @RequestMapping(value = "/newPlace", method = RequestMethod.GET)
    public String newPlace(Model model) {
        Place form = new Place();
        model.addAttribute("place", form);
        return "newPlace";
    }

    @RequestMapping(value = "/newPlace", method = RequestMethod.POST)
    public String saveRegister(@ModelAttribute("place")@Valid Place place,
                               BindingResult result, Model model, //
                               Principal principal, @RequestParam(name = "file1", required = false)MultipartFile file1,
                               @RequestParam(name = "file2", required = false)MultipartFile file2, @RequestParam(name = "file3", required = false)MultipartFile file3) {
        if (result.hasErrors()) {
            model.addAttribute("place", place);
            return "newPlace";
        }
        try {
            place=storageService.preStore(file1,file2,file3,place);
            userService.findUserByEmail(principal.getName());
            place.setUsarname(principal.getName());
            placeService.save(place);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("place", place);
            model.addAttribute("message","There is already exist such image");
            return "newPlace";
        }

        return "redirect:/userPage?username="+principal.getName();
    }

    @RequestMapping("/placeInfo")
    public String showApplications(Model model, @RequestParam("id")long id, Principal principal){
        Place place=placeService.updateView(id,1);
        List<Comment> list=commentService.getAllByIdPlace(id);
        List<Place> popular=placeService.getTop3PlaceByOrderByView();
        model.addAttribute("comments",list);
        model.addAttribute("app",place);
        model.addAttribute("popular",popular);
        Comment comment=new Comment();
        model.addAttribute("comment",comment);

        return "places";
    }
}
