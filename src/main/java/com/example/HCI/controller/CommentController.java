package com.example.HCI.controller;

import com.example.HCI.model.Comment;
import com.example.HCI.model.Likes;
import com.example.HCI.service.BlogService;
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

    @Autowired
    BlogService blogService;

    @RequestMapping("/deleteComment")
    public String deleteComment(@RequestParam("id") long id, @RequestParam("apId") long appId) {
        placeService.updateCommentNum(appId, -1);
        commentService.deleteById(id);
        return "redirect:/appInfo?id=" + appId + "#comment";
    }

    @RequestMapping(value = "/newComment", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> addComment(@RequestParam("comment") String text,@RequestParam("appId") long appId, Principal principal) {
        Comment comment=new Comment();
        comment.setComentText(text);
        comment.setUsername(principal.getName());
        comment.setIdPlace(appId);
        comment.setImage(userService.findUserByEmail(principal.getName()).getImage());
        Comment comment1=commentService.save(comment);
        placeService.updateCommentNum(comment.getIdPlace(), 1);
        return ResponseEntity.ok(comment1);
    }

    @RequestMapping("/deleteCommentBlog")
    public String deleteCommentBlog(@RequestParam("id") long id, @RequestParam("apId") long appId) {
        blogService.updateCommentNum(appId, -1);
        commentService.deleteById(id);
        return "redirect:/blogInfo?id=" + appId + "#comment";
    }

    @RequestMapping(value = "/newCommentBlog", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> addCommentBlog(@RequestParam("comment") String text,@RequestParam("appId") long appId, Principal principal) {
        Comment comment=new Comment();
        comment.setComentText(text);
        comment.setUsername(principal.getName());
        comment.setBlogID(appId);
        comment.setImage(userService.findUserByEmail(principal.getName()).getImage());
        Comment comment1=commentService.save(comment);
        blogService.updateCommentNum(comment.getBlogID(), 1);
        return ResponseEntity.ok(comment1);
    }
}
