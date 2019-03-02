package com.econo.hackday.contentsproxyblog.utils.controller;

import com.econo.hackday.contentsproxyblog.controller.PostController;
import com.econo.hackday.contentsproxyblog.model.Post;
import com.econo.hackday.contentsproxyblog.repository.PostRepository;
import com.econo.hackday.contentsproxyblog.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private PostService postService;

	@Test
	public void test() throws Exception {
		when(postService.getPostById(1L)).thenReturn(new Post("title1", "url1"));
		mockMvc.perform(get("/posts/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("show"))
				.andExpect(model().attributeExists("post"))
				.andDo(print());
		assertThat(postService.getPostById(1L).getUrl()).isEqualTo("url1");
	}
}
