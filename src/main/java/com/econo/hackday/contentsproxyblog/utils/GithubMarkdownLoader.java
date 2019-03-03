package com.econo.hackday.contentsproxyblog.utils;

import com.econo.hackday.contentsproxyblog.exception.GithubFileNotFoundException;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.util.EncodingUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubMarkdownLoader {
	private static final String PATH_SPLITTER = "/blob/master";

    public static String getContentsWithImages(String uri) throws IOException {
		return ImagePathConverter.convertRlativeUrisToAbsolute(getContents(uri), getInfoPath(uri));
	}

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
        return uri.split(PATH_SPLITTER)[1];
    }

    public static String getInfoPath(String uri){
    	return uri.split(PATH_SPLITTER)[0]+PATH_SPLITTER;
	}

}
