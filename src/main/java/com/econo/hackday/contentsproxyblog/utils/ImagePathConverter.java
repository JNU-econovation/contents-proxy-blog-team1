package com.econo.hackday.contentsproxyblog.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImagePathConverter {

	private static final String IMAGE_MARKDOWN_PATTERN = "!\\[(.)+\\]\\((.)+\\)";
	private static final String IMAGE_URI_PATTERN="[^\\(]+\\.(jpg|png|jpen|bmp)";

	private static Pattern pattern = Pattern.compile(IMAGE_MARKDOWN_PATTERN);
	private static Pattern imageUriPattern = Pattern.compile(IMAGE_URI_PATTERN);


	public static boolean isRelativeImage(String imageUri){
		try {
			new URL(imageUri);
		} catch (MalformedURLException e) {
			return true;
		}
		return false;
	}


	public static Boolean hasImage(String markdownContents){
		Matcher matcher = pattern.matcher(markdownContents);
		while (matcher.find()){
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
