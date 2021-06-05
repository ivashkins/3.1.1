package com.ivash.boot_ivasha.controller;


import com.ivash.boot_ivasha.model.User;
import com.ivash.boot_ivasha.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUsers(ModelMap map, Authentication authentication) {
        userService.getUsers(map, authentication);
        return "singleUser";
    }


    @GetMapping("/admin/{id}")
    @ResponseBody
    public User showUser(@PathVariable long id) {
        return userService.showUser(id);

    }

    @GetMapping("/admin")
    public String adminRole(ModelMap map,Authentication authentication) {
        userService.adminRole(map,authentication);
        return "admin";
    }


    @GetMapping("admin/new")
    public String newUser(ModelMap map) {
        userService.newUser(map);
        return "newUser2";
    }


    @PostMapping("/admin/create")
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(@PathVariable("id") long id, ModelMap map) {
        userService.edit(id, map);
        return "edit";
    }

    @GetMapping("admin/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PatchMapping("admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        System.out.println("+");

        System.out.println(user.getName() + " " + user.getRoles());
        userService.update(id,user);
        return "redirect:/admin";
    }

}
