package com.econo.hackday.contentsproxyblog.service;

import com.econo.hackday.contentsproxyblog.dto.PostSaveRequestDto;
import com.econo.hackday.contentsproxyblog.model.Hashtag;
import com.econo.hackday.contentsproxyblog.model.HashtagVariable;
import com.econo.hackday.contentsproxyblog.model.Post;
import com.econo.hackday.contentsproxyblog.repository.HashtagRepository;
import com.econo.hackday.contentsproxyblog.repository.HashtagVariableRepository;
import com.econo.hackday.contentsproxyblog.repository.PostRepository;
import com.econo.hackday.contentsproxyblog.utils.GithubMarkdownLoader;
import com.econo.hackday.contentsproxyblog.utils.HttpSessionUtil;
import com.econo.hackday.contentsproxyblog.utils.MarkdownParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private HashtagVariableRepository hashtagVariableRepository;

	public Iterable<Post> findAll() {
		return postRepository.findAll();
	}

	public Post getPostById(Long id) {
		return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 id의 Post를 찾을 수 없습니다."));
	}

	public String getHtml(Long id) throws IOException {
		String url = getPostById(id).getUrl();

		return MarkdownParser.convert(GithubMarkdownLoader.getContentsWithImages(url));
	}

	public void save(PostSaveRequestDto postSaveRequestDto, HttpSession httpSession, List<String> hashtagNames) {
		postSaveRequestDto.setWriter(HttpSessionUtil.getSessionedAccount(httpSession));
		Post post = postRepository.save(postSaveRequestDto.toEntity());

		for(String name: hashtagNames){
			Hashtag hashtag = postRepository.findHashtagByName(name).orElseThrow(()->new RuntimeException("해당 이름의 해시태그가 존재하지 않습니다."));
			hashtagVariableRepository.save(new HashtagVariable(hashtag ,post));
		}
	}

	public void increaseViewCount(Long id) {
		Post post = this.getPostById(id);
		post.increaseViewCount();
		postRepository.save(post);
	}

	public Iterable<Hashtag> findHashtags() {
		return postRepository.findHashtags();
	}
}
