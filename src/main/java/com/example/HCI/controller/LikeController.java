package com.example.HCI.controller;

import com.example.HCI.model.Likes;
import com.example.HCI.service.LikeService;
import com.example.HCI.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Calendar;

@Transactional
@Controller
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    PlaceService placeService;

    @RequestMapping(value = "/addLike", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getLike(@RequestParam("id") long id, @RequestParam("username") String username) {
        likeService.save(new Likes(username, id));
        placeService.updateLikes(id, 1);
        int likes = placeService.findById(id).getLikes();
        return ResponseEntity.ok(likes);
    }

    @RequestMapping(value = "/deleteLike", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> deletLike(@RequestParam("id") long id, @RequestParam("username") String username) {
        likeService.removeByUsernameAndAppId(username, id);
        placeService.updateLikes(id, -1);
        int likes = placeService.findById(id).getLikes();
        return ResponseEntity.ok(likes);
    }

    @RequestMapping(value = "/hasPut", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> putedLike(@RequestParam("id") long id, @RequestParam("username") String username) {
        int result = -1;
        if (likeService.existsByAppIdAndUsername(id, username));
            result = 1;
        return ResponseEntity.ok(result);

    }
}
