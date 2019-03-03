package com.econo.hackday.contentsproxyblog.controller;

import com.econo.hackday.contentsproxyblog.dto.AccountSaveRequestDto;
import com.econo.hackday.contentsproxyblog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@PostMapping("")
	public String signUp(AccountSaveRequestDto accountSaveRequestDto) {
		accountRepository.save(accountSaveRequestDto.toEntity());
		return "redirect:/";
	}

}
