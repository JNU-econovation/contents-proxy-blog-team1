package com.econo.hackday.contentsproxyblog.service;

import com.econo.hackday.contentsproxyblog.model.Post;
import com.econo.hackday.contentsproxyblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public Iterable<Post> findAll() {
		return postRepository.findAll();
	}

	public Post getPostById(Long id) {
		return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 id의 Post를 찾을 수 없습니다."));
	}
}
