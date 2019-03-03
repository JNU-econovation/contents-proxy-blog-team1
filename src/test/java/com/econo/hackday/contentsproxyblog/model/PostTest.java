package com.econo.hackday.contentsproxyblog.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class PostTest {

	Post post;

	@Before
	public void init() {
		post = new Post();
		HashtagVariable hashtagVariable = new HashtagVariable();
		hashtagVariable.setHashtag(new Hashtag(0l, "일상"));
		HashtagVariable hashtagVariable1 = new HashtagVariable();
		hashtagVariable1.setHashtag(new Hashtag(1l, "산책"));

		post.hashtagVariables = Arrays.asList(hashtagVariable, hashtagVariable1);
	}

	@Test
	public void hasTag() {
		assertThat(post.hasTag("일상")).isEqualTo(true);
		assertThat(post.hasTag("바다")).isEqualTo(false);
	}
}