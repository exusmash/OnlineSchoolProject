package com.example.onlineschoolproject.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllerMVC {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
