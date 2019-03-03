package com.econo.hackday.contentsproxyblog.dto;

import com.econo.hackday.contentsproxyblog.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HashtagDto {

	private String name;

	public Account toEntity() {
		return Account.builder()
				.name(name)
				.build();
	}
}
