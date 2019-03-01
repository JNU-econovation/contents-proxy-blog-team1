package com.econo.hackday.contentsproxyblog.exception;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String path) {
        super(path + "에 파일을 찾을수  없습니다.");
    }
}



