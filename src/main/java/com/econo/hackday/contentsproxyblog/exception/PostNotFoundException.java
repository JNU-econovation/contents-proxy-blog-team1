package com.econo.hackday.contentsproxyblog.exception;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException() {
		super("해당하는 id의 Post를 찾을 수 없습니다.");
	}
}
