package com.econo.hackday.contentsproxyblog.controller;

import com.econo.hackday.contentsproxyblog.repository.HashtagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(HashtagController.class)
public class HashtagControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	HashtagRepository hashtagRepository;

	@Test
	public void getHashtagFormTest() throws Exception {
		mockMvc.perform(get("/hashtags/form"))
				.andExpect(status().isOk())
				.andExpect(view().name("hashtag/tagForm"))
				.andDo(print());
	}

	@Test
	public void createPost() throws Exception {
		mockMvc.perform(post("/hashtags"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"))
				.andDo(print());
	}
}