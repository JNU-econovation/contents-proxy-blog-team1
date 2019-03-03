package com.econo.hackday.contentsproxyblog.repository;

import com.econo.hackday.contentsproxyblog.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
