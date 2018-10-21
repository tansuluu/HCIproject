package com.example.HCI.controller;

import com.example.HCI.model.Comment;
import com.example.HCI.service.CommentService;
import com.example.HCI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/newComment", method = RequestMethod.POST)
    public String saveComment(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, Principal principal, @RequestParam("appId") long appId){
        if (result.hasErrors()) {
            return "redirect:/placeInfo?id="+appId+"#comment";
        }
        comment.setUsername(principal.getName());
        comment.setIdPlace(appId);
        comment.setImage(userService.findUserByEmail(principal.getName()).getImage());
        commentService.save(comment);
        placeDAO.updateCommentNum(comment.getId_place(),1);
        return "redirect:/placeInfo?id="+appId+"#comment";
    }
}
