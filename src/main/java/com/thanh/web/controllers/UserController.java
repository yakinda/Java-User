package com.thanh.web.controllers;

import com.thanh.web.models.User;
import com.thanh.web.repositories.UserRepository;
import com.thanh.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<User> userList = userRepo.findAll();
        model.addAttribute("userList", userList);
        return "users/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "store")
    public String store(@ModelAttribute User user, @RequestParam("file") MultipartFile file) {
        String url = userService.saveFile(file);
        user.setAvatar(url);
        userRepo.save(user);

        return "redirect:/users";
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        User user = userRepo.getById(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/update")
    public String update(@ModelAttribute User userModel, @PathVariable Long id, @RequestParam("file") MultipartFile file) {
        User user = userRepo.getById(id);
        String url = userService.saveFile(file);
        user.setName(userModel.getName());
        user.setAge(userModel.getAge());
        user.setAddress(userModel.getAddress());
        user.setDob(userModel.getDob());
        user.setAvatar(url);
        userRepo.save(user);
        return "redirect:/users";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/delete")
    public String delete(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }
}
