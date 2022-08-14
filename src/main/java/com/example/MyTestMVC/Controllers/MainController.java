package com.example.MyTestMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String Greetings(Model model)
    {
        model.addAttribute("title", "Главная страница");
        return "index";
    }
    @GetMapping("/about")
    public String openAbout(Model model)
    {
        model.addAttribute("title", "О нас");
        return "about";
    }

}
