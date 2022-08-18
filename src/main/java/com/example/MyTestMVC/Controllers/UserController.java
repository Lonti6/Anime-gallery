package com.example.MyTestMVC.Controllers;

import com.example.MyTestMVC.Models.Role;
import com.example.MyTestMVC.Models.User;
import com.example.MyTestMVC.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String Registration(Model model)
    {
        model.addAttribute("title", "Регистрация");
        return "registration";
    }

/*    @PostMapping("/registration")
    public String RegistrationPost(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String nickName,
                                   Model model)
    {
        User user = new User(email, password, nickName);
        userRepository.save(user);

        return "index";
    }*/

    @PostMapping("/registration")
    public String RegistrationPost(User user, Map<String, Object> model)
    {
        User userFromDb = userRepository.findByMail(user.getMail());

        if (userFromDb != null)
        {
            model.put("message", "User is exist");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "authorization";
    }

    @GetMapping("/authorization")
    public String Authorization(Model model)
    {
        model.addAttribute("title", "Авторизация");
        return "authorization";
    }

    @PostMapping("/authorization")
    public String AuthorizationPost(Model model)
    {
        return "gallery";
    }


}