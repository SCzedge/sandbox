package com.bulma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/")
    String main() {
        return "/dashboard";
    }

    @GetMapping("/t")
    String mait() {
        return "/origin/index";
    }
}
