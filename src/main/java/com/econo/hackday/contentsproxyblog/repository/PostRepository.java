package com.econo.hackday.contentsproxyblog.repository;

import com.econo.hackday.contentsproxyblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
