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
import java.util.Calendar;

@Transactional
@Controller
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    PlaceService placeService;

    @RequestMapping(value = "/addLike", method = RequestMethod.GET, produces = "application/json")
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

    @RequestMapping(value = "/getFeedBack", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAction(@RequestParam String text, @RequestParam String feedback, @RequestParam String id, HttpServletRequest request) {
        AjaxResponseBody result = new AjaxResponseBody();
        String ip=historyDAO.getIP(request);
        History history=new History(ip, Calendar.getInstance().getTime(),text,feedback,"feedback",id);
        historyRepository.save(history);
        result.setMsg("feedbackThanks");
        return ResponseEntity.ok(result);
    }

}
