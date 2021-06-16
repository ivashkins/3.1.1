package com.ivash.boot_ivasha.controller;


import com.ivash.boot_ivasha.model.User;
import com.ivash.boot_ivasha.service.UserService;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/{id}")
    public User showUser(@PathVariable long id) {
        return userService.showUser(id);
    }

    @GetMapping("/admin/users")
    public List<User> adminRole(Authentication authentication) {
        return userService.adminRole(authentication);
    }


    @GetMapping("admin/new")
    public String newUser(ModelMap map) {
        userService.newUser(map);
        return "newUser2";
    }


    @PostMapping("/admin/create")
    public void create(@ModelAttribute("user") User user) {
        userService.create(user);
    }

    @GetMapping("/admin/{id}/edit")
    public void edit(@PathVariable("id") long id) {
        userService.edit(id);
    }

    @GetMapping("admin/{id}/delete")
    public Long delete(@PathVariable("id") long id) {
        userService.delete(id);
        return id;
    }

    @PatchMapping("admin/{id}")
    public User update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return userService.showUser(id);
    }

}
