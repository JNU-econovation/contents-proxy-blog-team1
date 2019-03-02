package com.econo.hackday.contentsproxyblog.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownToHtmlConverterTest {

	@Test
	public void convertTest() {
		String html = MarkdownToHtmlConverter.convert("This is *Sparta*");

		assertThat(html).isEqualTo("<p>This is <em>Sparta</em></p>\n");

		html = MarkdownToHtmlConverter.convert("http://github.com/vsch/flexmark-java");
		assertThat(html).isEqualTo("<p><a href=\"http://github.com/vsch/flexmark-java\" class=\"my-autolink-class\">http://github.com/vsch/flexmark-java</a></p>\n");

		html = MarkdownToHtmlConverter.convert("hello\nworld");
		assertThat(html).isEqualTo("<p>hello<br/>world</p>\n");
	}
}