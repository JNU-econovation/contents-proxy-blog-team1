package com.econo.hackday.contentsproxyblog.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<HashtagVariable> hashtags;

	@ManyToOne
	private Account writer;

	@Builder
	public Post(String title, String url, Account writer){
		this.title = title;
		this.url = url;
		this.writer = writer;
	}
}
