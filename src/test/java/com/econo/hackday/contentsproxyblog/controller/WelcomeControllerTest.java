package com.econo.hackday.contentsproxyblog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@WebMvcTest(WelcomeController.class)
public class WelcomeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void getWelcomeTest() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(redirectedUrl("/posts"))
				.andDo(print());
	}
}