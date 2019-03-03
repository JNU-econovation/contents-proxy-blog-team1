package com.econo.hackday.contentsproxyblog.repository;

import com.econo.hackday.contentsproxyblog.model.Hashtag;
import com.econo.hackday.contentsproxyblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("SELECT hashtag FROM Hashtag hashtag ORDER BY hashtag.name")
	@Transactional(readOnly = true)
	List<Hashtag> findHashtags();

}
