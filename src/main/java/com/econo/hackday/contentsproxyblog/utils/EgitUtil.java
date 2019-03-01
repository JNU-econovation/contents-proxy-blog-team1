package com.econo.hackday.contentsproxyblog.utils;

import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.util.EncodingUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EgitUtil {

    public static ContentsService contentsService = new ContentsService();

    public static String getContents(String uri) throws IOException {
        String filePath = getFilePath(uri);

        List<RepositoryContents> contents = contentsService.getContents(createRepositoryId(uri), filePath);
        if (contents.size() == 0) {
            throw new FileNotFoundException(filePath);
        }
        String content = contents.get(0).getContent();

        return new String(EncodingUtils.fromBase64(content));
    }

    public static RepositoryId createRepositoryId(String uri) {
        String[] tokens = uri.split("/");
        String owner = tokens[3];
        String repository = tokens[4];

        return RepositoryId.create(owner, repository);
    }

    public static String getFilePath(String uri) {
        return uri.split("/blob")[1];
    }
}
