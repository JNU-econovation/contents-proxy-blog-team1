package com.econo.hackday.contentsproxyblog.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HashtagVariableTest {

	HashtagVariable hashtagVariable;

	@Before
	public void init(){
		hashtagVariable= new HashtagVariable();
		hashtagVariable.setHashtag(new Hashtag(0l, "일상"));
	}
	@Test
	public void match() {
		assertThat(hashtagVariable.match("일상")).isEqualTo(true);
	}
}