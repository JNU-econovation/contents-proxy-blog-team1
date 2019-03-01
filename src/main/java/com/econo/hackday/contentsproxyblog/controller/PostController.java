package com.econo.hackday.contentsproxyblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    public void getPosts() {

    }

    @GetMapping("/{id}")
    public void getPostDetail() {

    }

    @PostMapping
    public void createPost() {

    }

}
