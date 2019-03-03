package com.econo.hackday.contentsproxyblog.dto;

import com.econo.hackday.contentsproxyblog.model.Account;
import com.econo.hackday.contentsproxyblog.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveRequestDto {

	private String title;
	private String url;
	private Account writer;

	public Post toEntity() {
		return Post.builder()
				.title(title)
				.url(url)
				.writer(writer)
				.build();
	}
}
