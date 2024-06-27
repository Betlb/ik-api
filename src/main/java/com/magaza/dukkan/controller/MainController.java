package com.magaza.dukkan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main/index.html")
    public String getIndexPage() {
        return "index"; // Bu, resources/static/index.html dosyasına yönlendirecek
    }
}