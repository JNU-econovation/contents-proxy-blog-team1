package com.econo.hackday.contentsproxyblog.controller;


import com.econo.hackday.contentsproxyblog.model.Account;
import com.econo.hackday.contentsproxyblog.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AccountService accountService;

	@Test
	public void singUpTest() throws Exception {
		mockMvc.perform(post("/accounts"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"))
				.andDo(print());
	}

	@Test
	public void showLoginFormTest() throws Exception {
		mockMvc.perform(get("/accounts/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("accounts/login"))
				.andDo(print());
	}

	@Test
	public void loginTest() throws Exception {
		when(accountService.findByAccountId("esp2ar0"))
				.thenReturn(Account.builder()
						.accountId("esp2ar0")
						.name("changhwan")
						.password("1234")
						.email("esp2ar0@gmail.com")
						.build());

		mockMvc.perform(post("/accounts/login")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("accountId", "esp2ar0")
					.param("password", "1234"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"))
				.andDo(print());
	}

	@Test
	public void logoutTest() throws Exception {
		mockMvc.perform(get("/accounts/logout"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"))
				.andDo(print());
	}

}