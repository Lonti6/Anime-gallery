package com.example.MyTestMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/registration")
    public String Registration(Model model)
    {
        model.addAttribute("title", "Регистрация");
        return "registration";
    }
    @GetMapping("/authorization")
    public String Authorization(Model model)
    {
        model.addAttribute("title", "Авторизация");
        return "authorization";
    }
}