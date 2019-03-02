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
    //(^[.*]$)6
	private static final String PATH_SPLITTER = "/blob/master";
    private static final String IMAGE_MARKDOWN_PATTERN = "!\\[(.)+\\]\\((.)+\\)";
    private static final String IMAGE_URI_PATTERN="[^\\(]+\\.(jpg|png|jpen|bmp)";

    private static Pattern pattern = Pattern.compile(IMAGE_MARKDOWN_PATTERN);
    private static Pattern imageUriPattern = Pattern.compile(IMAGE_URI_PATTERN);

    public static String getContents(String uri) throws IOException {
        String filePath = getFilePath(uri);
        ContentsService contentsService = new ContentsService();

        List<RepositoryContents> contents = contentsService.getContents(RepositoryId.createFromUrl(uri), filePath);
        if (contents.size() == 0) {
            throw new GithubFileNotFoundException(filePath);
        }

        return convertRlativeUrisToAbsolute(
        		new String(EncodingUtils.fromBase64(contents.get(0).getContent()))
				,getInfoPath(uri)
		);
    }

    public static String getFilePath(String uri) {
        return uri.split(PATH_SPLITTER)[1];
    }

    public static String getInfoPath(String uri){
    	return uri.split(PATH_SPLITTER)[0]+PATH_SPLITTER;
	}

    public static Boolean hasImage(String markdownContents){
        Matcher matcher = pattern.matcher(markdownContents);
        while (matcher.find()){
            return true;
        }
        return false;
    }

    public static boolean isRelativeImage(String imageUri){
        try {
            new URL(imageUri);
        } catch (MalformedURLException e) {
            return true;
        }
        return false;
    }

    public static String convertRlativeUrisToAbsolute(String markdownContents, String infoPath){
    	Matcher matcher = pattern.matcher(markdownContents);
		StringBuffer replacedString = new StringBuffer();
		while(matcher.find()) {
			String convertedURI = toAbsolute(matcher.group(), infoPath);
			matcher.appendReplacement(replacedString, convertedURI);
		}
		matcher.appendTail(replacedString);

		return replacedString.toString();
	}

	private static String toAbsolute(String imageMarkdown, String infoPath) {
    	Matcher matcher = imageUriPattern.matcher(imageMarkdown);
		StringBuffer replacedString = new StringBuffer();
		String filePath="";
		if(matcher.find()){
			 filePath= matcher.group();
		}
		if(!isRelativeImage(filePath)){
			return imageMarkdown;
		}
		matcher.appendReplacement(replacedString, infoPath+"/"+matcher.group()+"?raw=true");

		matcher.appendTail(replacedString);

		return replacedString.toString();
	}
}
