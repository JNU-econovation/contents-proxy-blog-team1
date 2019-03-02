package com.econo.hackday.contentsproxyblog.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownParserTest {

	@Test
	public void convertTest() {
		String html = MarkdownParser.convert("This is *Sparta*");
		assertThat(html).isEqualTo("<p>This is <em>Sparta</em></p>\n");

		html = MarkdownParser.convert("hello\nworld");
		assertThat(html).isEqualTo("<p>hello<br/>world</p>\n");
	}
}