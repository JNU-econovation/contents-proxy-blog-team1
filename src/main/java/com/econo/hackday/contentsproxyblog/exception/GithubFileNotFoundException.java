package com.econo.hackday.contentsproxyblog.exception;

public class GithubFileNotFoundException extends RuntimeException {

    public GithubFileNotFoundException(String path) {
        super(path + "에 파일을 찾을수  없습니다.");
    }
}



