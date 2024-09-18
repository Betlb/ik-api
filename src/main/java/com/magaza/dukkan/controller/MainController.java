package com.magaza.dukkan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndexPage() {
        return "index"; // Bu, templates/index.html dosyasına yönlendirecek
    }
}
