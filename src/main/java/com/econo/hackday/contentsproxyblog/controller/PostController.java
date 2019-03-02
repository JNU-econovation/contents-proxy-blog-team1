package com.econo.hackday.contentsproxyblog.controller;

import com.econo.hackday.contentsproxyblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.get)
        return "index";
    }

    @PostMapping("")
    public String createPost() {
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "show";
    }

    @GetMapping("/form")
    public String getPostForm() {
        return "form";
    }

}
