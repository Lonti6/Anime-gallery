package com.example.MyTestMVC.Controllers;

import com.example.MyTestMVC.Models.Evaluations;
import com.example.MyTestMVC.Repositories.LikeRepository;
import com.example.MyTestMVC.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LikeController {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/liking")
    public String getLikes(Model model,
                           @RequestParam long postId,
                           HttpServletResponse response) {

        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var userID = userRepository.findByMail(((UserDetails) principal).getUsername()).getId();

        Evaluations ev = likeRepository.findLikeByPostAndUser(postId, userID);

        if (ev == null) {
            ev = new Evaluations(userID, postId, true);
            likeRepository.save(ev);
            System.out.println("Like is done");
        } else {
            ev.setGood(!ev.isGood());
            likeRepository.save(ev);
        }

        headerAdd(ev, response, postId);

        return "liking";
    }

    @GetMapping("/postLiked")
    public String postLiked(Model model,
                           @RequestParam long postId,
                           HttpServletResponse response) {

        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var userID = userRepository.findByMail(((UserDetails) principal).getUsername()).getId();

        Evaluations ev = likeRepository.findLikeByPostAndUser(postId, userID);

        headerAdd(ev, response, postId);

        return "postLiked";
    }

    private void headerAdd(Evaluations ev, HttpServletResponse response, long postId)
    {
        var s = likeRepository.findAllByPostIdWithoutNull(postId).size();

        response.addHeader("likeCount", String.valueOf(s));

        response.addHeader("isGood",
                (ev == null || !ev.isGood()) ? "false" : "true");
    }
}
