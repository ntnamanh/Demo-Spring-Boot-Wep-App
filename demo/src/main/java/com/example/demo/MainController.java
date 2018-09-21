package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/welcome")
public class MainController {

    @GetMapping
    public String welcome(Model model){
        model.addAttribute("msg","ahihi do cho");
        return "welcome";
    }
}
