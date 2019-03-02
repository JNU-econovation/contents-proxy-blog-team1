package com.econo.hackday.contentsproxyblog.utils;

import com.econo.hackday.contentsproxyblog.exception.GithubFileNotFoundException;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.util.EncodingUtils;

import java.io.IOException;
import java.util.List;

public class GithubMarkdownLoader {

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
}
