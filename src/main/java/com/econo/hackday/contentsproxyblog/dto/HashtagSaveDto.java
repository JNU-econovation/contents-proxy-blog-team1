package com.econo.hackday.contentsproxyblog.dto;

import com.econo.hackday.contentsproxyblog.model.Account;
import com.econo.hackday.contentsproxyblog.model.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HashtagSaveDto {

	private String name;

	public Hashtag toEntity() {
		return Hashtag.builder()
				.name(name)
				.build();
	}
}
