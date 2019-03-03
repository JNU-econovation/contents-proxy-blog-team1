package com.econo.hackday.contentsproxyblog.exception;

public class UndefinedImageExtension extends RuntimeException {
	public UndefinedImageExtension(String extension) {
		super(extension + "은 정의되지 않은 확장자입니다.");
	}

}
