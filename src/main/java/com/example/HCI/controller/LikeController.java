package com.example.HCI.controller;

import com.example.HCI.model.Likes;
import com.example.HCI.service.LikeService;
import com.example.HCI.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Transactional
@Controller
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    PlaceService placeService;

    @GetMapping(value = "/addLike")
    public String getLike(@RequestParam("id") long id, @RequestParam("username") String username) {
        likeService.save(new Likes(username, id));
        placeService.updateLikes(id,1);
        return "redirect:/placeInfo?id="+id+"#likes";
    }
    @GetMapping(value = "/deleteLike")
    public String deletLike(@RequestParam("id") long id, @RequestParam("username") String username) {
        likeService.removeByUsernameAndAppId(username,id);
        placeService.updateLikes(id,-1);
        return "redirect:/placeInfo?id="+id+"#likes";
    }

}
