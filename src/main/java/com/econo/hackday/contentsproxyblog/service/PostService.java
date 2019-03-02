package com.econo.hackday.contentsproxyblog.service;

import com.econo.hackday.contentsproxyblog.exception.PostNotFoundException;
import com.econo.hackday.contentsproxyblog.model.Post;
import com.econo.hackday.contentsproxyblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public Post getPostById(Long id) {
		return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
	}
}
