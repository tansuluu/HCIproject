package com.example.HCI.controller;

import com.example.HCI.model.Place;
import com.example.HCI.model.Post;
import com.example.HCI.model.User;
import com.example.HCI.service.PlaceService;
import com.example.HCI.service.PostService;
import com.example.HCI.service.StorageService;
import com.example.HCI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@Transactional
public class UserController {

    @Autowired
    private StorageService storageService;

    @Autowired
    UserService userService;

    @Autowired
    PlaceService placeService;

    @Autowired
    PostService postService;

    @RequestMapping("/userPage")
    public String showUser(Model model, @RequestParam("username")String email){
        User user=userService.findUserByEmail(email);
        List<Place> list=placeService.findByUsarname(email);
        model.addAttribute("places",list);
        model.addAttribute("post",new Post());
        model.addAttribute("user",user);
        model.addAttribute("posts",postService.findAllByUser(email));
        return "profile";
    }
    @GetMapping("/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        String mimeType = "";
        try {
            mimeType = Files.probeContentType(file.getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    @GetMapping("/up-avatar/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename) {
        Resource file = storageService.loadAvatar(filename);
        String mimeType = "";
        try {
            mimeType = Files.probeContentType(file.getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")

                .body(file);
    }

}
