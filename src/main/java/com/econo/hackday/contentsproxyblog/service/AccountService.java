package com.econo.hackday.contentsproxyblog.service;

import com.econo.hackday.contentsproxyblog.dto.AccountSaveRequestDto;
import com.econo.hackday.contentsproxyblog.model.Account;
import com.econo.hackday.contentsproxyblog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	public void save(AccountSaveRequestDto accountSaveRequestDto) {
		accountRepository.save(accountSaveRequestDto.toEntity());
	}

	public Account findByAccountId(String accountId) {
		return accountRepository.findByAccountId(accountId).orElseThrow(() -> new NoSuchElementException("Invalid accountId"));
	}
}
