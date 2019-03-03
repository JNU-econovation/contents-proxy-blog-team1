package com.econo.hackday.contentsproxyblog.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

	public Post(String title, String url){
		this.title = title;
		this.url = url;
	}
}
