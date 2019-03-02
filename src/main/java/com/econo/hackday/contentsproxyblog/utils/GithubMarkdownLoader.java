package com.econo.hackday.contentsproxyblog.utils;

import com.econo.hackday.contentsproxyblog.exception.GithubFileNotFoundException;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.util.EncodingUtils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubMarkdownLoader {
    //(^[.*]$)6
    private static final String IMAGE_MARKDOWN_PATTERN = "!\\[(.)+\\]\\((.)+\\)";
    private static Pattern pattern = Pattern.compile(IMAGE_MARKDOWN_PATTERN);

    public static String getContents(String uri) throws IOException {
        String filePath = getFilePath(uri);
        ContentsService contentsService = new ContentsService();

        List<RepositoryContents> contents = contentsService.getContents(RepositoryId.createFromUrl(uri), filePath);
        if (contents.size() == 0) {
            throw new GithubFileNotFoundException(filePath);
        }

        return new String(EncodingUtils.fromBase64(contents.get(0).getContent()));
    }

    public static String getFilePath(String uri) {
        return uri.split("/blob/master")[1];
    }

    public static Boolean hasRelativeImage(String imageMarkdown){
        Matcher matcher = pattern.matcher(imageMarkdown);
        while (matcher.find()){
            return true;
        }

        return false;
    }
}
