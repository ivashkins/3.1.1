package com.ivash.boot_ivasha.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HtmlController {

    @GetMapping("/user")
    public String getUsers() {
        return "singleUser";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
