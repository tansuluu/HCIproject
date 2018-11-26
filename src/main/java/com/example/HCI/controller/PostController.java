package com.example.HCI.controller;


import com.example.HCI.model.Comment;
import com.example.HCI.model.Post;
import com.example.HCI.service.PostService;
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
public class PostController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String saveComment(@ModelAttribute("post") @Valid Post post, BindingResult result, Principal principal, @RequestParam("username") String username){
        if (result.hasErrors()) {
            return "redirect:/userPage?username="+username;
        }
        post.setUsername(principal.getName());
        post.setUser(username);
        post.setImage(userService.findUserByEmail(principal.getName()).getImage());
        postService.save(post);
        return "redirect:/userPage?username="+username;
    }
    @RequestMapping(value = "/newPost", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> addComment(@RequestParam("post") String text, @RequestParam("username") String username, Principal principal) {
        Post post=new Post();
        post.setPostText(text);
        post.setUsername(principal.getName());
        post.setUser(username);
        post.setImage(userService.findUserByEmail(principal.getName()).getImage());
        Post post1=postService.save(post);
        return ResponseEntity.ok(post1);
    }

    @RequestMapping("/deletePost")
    public String deletePost(@RequestParam("id") long id, @RequestParam("username") String username){
        postService.deleteById(id);
        return "redirect:/userPage?username="+username;
    }
}
