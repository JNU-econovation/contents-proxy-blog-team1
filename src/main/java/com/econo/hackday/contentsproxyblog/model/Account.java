package com.econo.hackday.contentsproxyblog.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	private String accountId;

	private String name;

	private String password;

	private String email;

	@Builder
	public Account(String accountId, String name, String password, String email) {
		this.accountId = accountId;
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public boolean match(String password) {
		return this.password.equals(password);
	}
}
