package com.econo.hackday.contentsproxyblog.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hashtag {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
//
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hashtag")
//	private List<HashtagVariable> HashtagVariable;

}
