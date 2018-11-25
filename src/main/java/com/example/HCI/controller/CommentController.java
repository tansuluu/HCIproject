package com.example.HCI.controller;

import com.example.HCI.model.Comment;
import com.example.HCI.model.Likes;
import com.example.HCI.service.CommentService;
import com.example.HCI.service.PlaceService;
import com.example.HCI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

@Transactional
@Controller
public class CommentController {

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    PlaceService placeService;

    @RequestMapping(value = "/newComment", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> addComment(@RequestParam("comment") String comment,@RequestParam("appId") long appId, Principal principal) {
        System.out.print("hererererrerrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        return ResponseEntity.ok(7);
    }
}
