package com.econo.hackday.contentsproxyblog.dto;

import com.econo.hackday.contentsproxyblog.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountSaveRequestDto {

	private String accountId;
	private String name;
	private String password;
	private String email;

	public Account toEntity() {
		return Account.builder()
				.accountId(accountId)
				.name(name)
				.password(password)
				.email(email)
				.build();
	}
}
