package com.example.MyTestMVC.Controllers;

import com.example.MyTestMVC.Models.Evaluations;
import com.example.MyTestMVC.Models.Post;
import com.example.MyTestMVC.Repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LikeController {

    @Autowired
    LikeRepository likeRepository;

    @GetMapping("/phones")
    public String getLikes(Model model,
                           @RequestParam long userID,
                           @RequestParam long postId,
                           @RequestParam boolean isGood)
    {
        likeRepository.save(new Evaluations(userID, postId, isGood));
        //likeRepository.save(new Evaluations(3, 3, true));
        return "phones";
    }
}
