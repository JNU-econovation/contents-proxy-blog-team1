package com.econo.hackday.contentsproxyblog.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImagePathConverter {

	static final String RAW_PARAM = "?raw=true";
	static final String IMAGE_MARKDOWN_PATTERN = "!\\[(.)+\\]\\((.)+\\)";
	static final String IMAGE_URI_PATTERN = "[^\\(]+\\.(jpg|png|jpen|bmp)";

	private static Pattern imageMarkdownPattern = Pattern.compile(IMAGE_MARKDOWN_PATTERN);
	private static Pattern imageUriPattern = Pattern.compile(IMAGE_URI_PATTERN);

	public static boolean isRelativeImage(StringBuilder imageUri) {
		try {
			new URL(imageUri.toString());
		} catch (MalformedURLException e) {
			return true;
		}
		return false;
	}

	public static Boolean hasImage(String markdownContents) {
		Matcher matcher = imageMarkdownPattern.matcher(markdownContents);
		while (matcher.find()) {
			return true;
		}
		return false;
	}

	public static String convertRlativeUrisToAbsolute(String markdownContents, String infoPath) {
		Matcher matcher = imageMarkdownPattern.matcher(markdownContents);
		StringBuffer replacedString = new StringBuffer();

		while (matcher.find()) {
			matcher.appendReplacement(replacedString, toAbsoluteImagePath(matcher.group(), infoPath));
		}
		matcher.appendTail(replacedString);

		return replacedString.toString();
	}

	public static String toAbsoluteImagePath(String imageMarkdown, String infoPath) {
		Matcher matcher = imageUriPattern.matcher(imageMarkdown);
		StringBuffer replacedString = new StringBuffer();
		StringBuilder filePath = new StringBuilder("");

		if (matcher.find()) {
			filePath.append(matcher.group());
		}
		if (!isRelativeImage(filePath)) {
			return imageMarkdown;
		}
		if(filePath.charAt(0)!= '/'){
			filePath.insert(0,"/");
		}
		matcher.appendReplacement(replacedString,
				new StringBuilder(infoPath).append(filePath).append(RAW_PARAM).toString());
		matcher.appendTail(replacedString);
		return replacedString.toString();
	}
}
