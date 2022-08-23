package com.example.MyTestMVC.Controllers;

import com.example.MyTestMVC.Models.Post;
import com.example.MyTestMVC.Repositories.LikeRepository;
import com.example.MyTestMVC.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Controller
public class GalleryController {

    private final int postsOnPage = 6;

    @Autowired
    private PostRepository postRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/gallery")
    public String openGallery(Model model) {
        model.addAttribute("title", "Галерея");
        model.addAttribute("startPage", 0);

        Iterable<Post> posts = getPosts("NONE", 0);
        model.addAttribute("posts", posts);

        Post p = new Post();

        finalPrepare(model);

        return "gallery";
    }

    @PostMapping("/gallery")
    public String openGalleryPost(@RequestParam String sortType,
                                  @RequestParam int pageNum,
                                  Model model) {

        model.addAttribute("title", "Галерея");
        model.addAttribute("sortType", sortType);
        model.addAttribute("startPage", pageNum);

        Iterable<Post> posts;

        posts = getPosts(sortType, pageNum);
        model.addAttribute("posts", posts);

        finalPrepare(model);

        return "gallery";
    }

    @GetMapping("/gallery/create")
    public String openGalleryCreate(Model model) {
        model.addAttribute("title", "Создание записи");
        return "galleryCreate";
    }

    @PostMapping("/gallery/create")
    public String createPost(@RequestParam String title,
                             @RequestParam int rating,
                             @RequestParam String href,
                             @RequestParam("file") MultipartFile file,
                             Model model) throws IOException {

        Post post = new Post(title, rating, href);

        if (file != null && !file.getOriginalFilename().equals("")) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists())
                uploadDir.mkdir();

            String uuidFile = UUID.randomUUID().toString();

            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            String fullPath = uploadPath + "/" + resultFileName;

            file.transferTo(new File(fullPath));

            post.setHref("images/loaded/" + resultFileName);
        }


        postRepository.save(post);

        return "galleryCreate";
    }

    private Iterable<Post> getPosts(String sortType, int pageNum) {
        var req = PageRequest.of(pageNum, postsOnPage);

        if (sortType.equals("name"))
            return postRepository.findAll(req.withSort(Sort.by(Sort.Direction.ASC, "title")));
        else if (sortType.equals("rating"))
            return postRepository.findAll(req.withSort(Sort.by(Sort.Direction.DESC, "rating")));
        else if (sortType.equals("newToOld"))
            return postRepository.findAll(req.withSort(Sort.by(Sort.Direction.DESC, "id")));

        return postRepository.findAll(req);
    }

    private void finalPrepare(Model model) {
        var temp = ((int) postRepository.count()/postsOnPage)+1;
        var a = Arrays.asList(new Integer[ (temp <8) ? temp : 7]);
        model.addAttribute("pageList", a);
        model.addAttribute("pageCount", temp);
    }
}