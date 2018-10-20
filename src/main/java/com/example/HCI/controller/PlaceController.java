package com.example.HCI.controller;


import com.example.HCI.model.Place;
import com.example.HCI.service.StorageService;
import com.example.HCI.service.UserService;
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

@Controller
@Transactional
public class PlaceController {

    @Autowired
    StorageService storageService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/newPlace", method = RequestMethod.GET)
    public String newPlace(Model model) {
        Place form = new Place();
        model.addAttribute("place", form);
        return "newPlace";
    }

    @RequestMapping(value = "/newPlace", method = RequestMethod.POST)
    public String saveRegister(@ModelAttribute("place")@Valid Place app,
                               BindingResult result, Model model, //
                               Principal principal, @RequestParam(name = "file1", required = false)MultipartFile file1,
                               @RequestParam(name = "file2", required = false)MultipartFile file2, @RequestParam(name = "file3", required = false)MultipartFile file3) {
        if (result.hasErrors()) {
            model.addAttribute("place", app);
            return "newPlace";
        }
        try {
            app=storageService.preStore(file1,file2,file3,app);
            userService.findUserByEmail(principal.getName());
            app.setUsarname(principal.getName());
            addPlace(app);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("place", app);
            model.addAttribute("message","There is already exist such image");
            return "newPlace";
        }

        return "redirect:/userPage?username="+principal.getName();
    }
}
