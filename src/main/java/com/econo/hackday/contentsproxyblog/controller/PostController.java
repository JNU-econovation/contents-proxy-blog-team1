package com.econo.hackday.contentsproxyblog.controller;

import com.econo.hackday.contentsproxyblog.dto.PostSaveRequestDto;
import com.econo.hackday.contentsproxyblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("")
	public String getPosts(Model model) {
		model.addAttribute("posts", postService.findAll());
		return "post/index";
	}

	@GetMapping(value = "", params = {"hashtag"})
	public String getFilteredPosts(Model model, @RequestParam String hashtag) {
		model.addAttribute("posts", postService.findAllByHashtag(hashtag));
		return "post/index";
	}

	@PostMapping("")
	public String createPost(PostSaveRequestDto postSaveRequestDto,
							 HttpSession httpSession,
							 @RequestParam(value = "hashtags", defaultValue = "false") List<String> hashtags) {
		postService.save(postSaveRequestDto, httpSession, hashtags);
		return "redirect:/posts";
	}

	@GetMapping("/{id}")
	public String getPostDetail(@PathVariable Long id, Model model) throws IOException {
		postService.increaseViewCount(id);
		model.addAttribute("post", postService.getPostById(id));
		model.addAttribute("contents", postService.getHtml(id));
		return "post/show";
	}

	@GetMapping("/form")
	public String getPostForm(Model model) {
		model.addAttribute("hashtags", postService.findHashtags());
		return "post/form";
	}

}
