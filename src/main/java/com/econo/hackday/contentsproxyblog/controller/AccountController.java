package com.econo.hackday.contentsproxyblog.controller;

import com.econo.hackday.contentsproxyblog.dto.AccountSaveRequestDto;
import com.econo.hackday.contentsproxyblog.model.Account;
import com.econo.hackday.contentsproxyblog.service.AccountService;
import com.econo.hackday.contentsproxyblog.utils.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("")
	public String signUp(AccountSaveRequestDto accountSaveRequestDto) {
		accountService.save(accountSaveRequestDto);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "accounts/login";
	}

	@PostMapping("/login")
	public String login(String accountId, String password, HttpSession httpSession) {
		Account account = accountService.findByAccountId(accountId);

		if (!account.match(password)) {
			throw new RuntimeException("password mismatch");
		}

		httpSession.setAttribute(HttpSessionUtil.ACCOUNT_SESSION_KEY, account);
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute(HttpSessionUtil.ACCOUNT_SESSION_KEY);
		return "redirect:/";
	}

}
