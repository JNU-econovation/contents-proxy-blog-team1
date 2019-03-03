package com.econo.hackday.contentsproxyblog.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

	Account account;

	@Before
	public void setup() {
		account = Account.builder()
				.accountId("esp2ar0")
				.name("changhwan")
				.password("1234")
				.email("esp2ar0@gmail.com")
				.build();
	}

	@Test
	public void matchTest_false() {
		assertThat(account.match("123")).isFalse();
	}

	@Test
	public void matchTest_true() {
		assertThat(account.match("1234")).isTrue();
	}
}