package com.econo.hackday.contentsproxyblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String getWelcome() {
		return "redirect:/posts";
	}

}
