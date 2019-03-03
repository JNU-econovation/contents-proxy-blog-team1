package com.econo.hackday.contentsproxyblog.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String url;

	@ManyToOne
	private Account writer;

	private Long viewCount;

	@Builder
	public Post(String title, String url, Account writer){
		this.title = title;
		this.url = url;
		this.writer = writer;
		this.viewCount = 0l;
	}

	public void increaseViewCount() {
		this.viewCount++;
	}
}
