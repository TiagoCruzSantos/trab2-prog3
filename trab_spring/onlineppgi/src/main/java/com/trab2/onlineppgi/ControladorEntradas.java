package com.trab2.onlineppgi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorEntradas {
    
	@GetMapping("/")
    public String homePage() {
        return "index";
    }
}