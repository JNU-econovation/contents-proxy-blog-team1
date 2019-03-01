package com.econo.hackday.contentsproxyblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping("")
    public String getPosts() {
        return "index";
    }

    @PostMapping("")
    public String createPost() {
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String getPostDetail() {
        return "show";
    }

    @GetMapping("/form")
    public String getPostForm() {
        return "form";
    }

}