package com.econo.hackday.contentsproxyblog.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class HashtagVariable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hashtag_id")
	@NonNull
	private Hashtag hashtag;

	@ManyToOne
	@JoinColumn(name = "post_id")
	@NonNull
	private Post post;

	public boolean match(String tagName){
		return this.hashtag.getName().equals(tagName);
	}

}
