package com.econo.hackday.contentsproxyblog.controller;

import com.econo.hackday.contentsproxyblog.dto.PostSaveRequestDto;
import com.econo.hackday.contentsproxyblog.model.Hashtag;
import com.econo.hackday.contentsproxyblog.repository.HashtagRepository;
import com.econo.hackday.contentsproxyblog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/hashtags")
public class HashtagController {

	@Autowired
	HashtagRepository hashtagRepository;

	@GetMapping("/form")
	public String getHashtagForm() {
		return "hashtag/tagForm";
	}

	@PostMapping("")
	public String createPost(Hashtag hashtag, HttpSession httpSession) {
		hashtagRepository.save(hashtag);
		return "redirect:/";
	}


}
