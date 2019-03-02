package com.econo.hackday.contentsproxyblog.utils;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class MarkdownParser {


	public static String convert(String markdown) {
		MutableDataHolder options = new MutableDataSet();
		options.setFrom(ParserEmulationProfile.MARKDOWN);

		// change soft break to hard break
		options.set(HtmlRenderer.SOFT_BREAK, "<br/>");

		Parser parser = Parser.builder(options).build();
		HtmlRenderer renderer = HtmlRenderer.builder(options).build();

		Node document = parser.parse(markdown);
		return renderer.render(document);
	}
}
