package com.econo.hackday.contentsproxyblog.controller;

import com.econo.hackday.contentsproxyblog.dto.HashtagSaveDto;
import com.econo.hackday.contentsproxyblog.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String createPost(HashtagSaveDto hashtagSaveDto) {
		hashtagRepository.save(hashtagSaveDto.toEntity());
		return "redirect:/";
	}


}
