package com.econo.hackday.contentsproxyblog.controller;

import com.econo.hackday.contentsproxyblog.model.Post;
import com.econo.hackday.contentsproxyblog.service.PostService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	public void GET_Posts_Id() throws Exception {
		when(postService.getPostById(1L)).thenReturn(Post.builder()
				.title("title1")
				.url("url1")
				.writer(null)
				.build());

		mockMvc.perform(get("/posts/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("post/show"))
				.andExpect(model().attributeExists("post"))
				.andDo(print());
		assertThat(postService.getPostById(1L).getUrl()).isEqualTo("url1");
	}

	@Test
	public void GET_Posts() throws Exception {
		Post post1 = Post.builder()
				.title("title1")
				.url("url1")
				.writer(null)
				.build();
		Post post2 = Post.builder()
				.title("title2")
				.url("url2")
				.writer(null)
				.build();
		Post[] posts = {
				post1, post2
		};

		when(postService.findAll()).thenReturn(Arrays.asList(posts));

		mockMvc.perform(get("/posts"))
				.andExpect(status().isOk())
				.andExpect(view().name("post/index"))
				.andExpect(model().attributeExists("posts"))
				.andExpect(model().attribute("posts", IsCollectionWithSize.hasSize(2)))
				.andDo(print());
		assertThat(postService.findAll()).contains(post1).contains(post2);
	}

	@Test
	public void POST_Posts() throws Exception {
		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("hashtags", "a")
				.param("hashtags", "b"))
				.andExpect(status().is3xxRedirection())
				.andDo(print());
	}
}
