package com.econo.hackday.contentsproxyblog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HashtagVariable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name ="hashtag_id")
	private Hashtag hashtag;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private  Post post;

}
