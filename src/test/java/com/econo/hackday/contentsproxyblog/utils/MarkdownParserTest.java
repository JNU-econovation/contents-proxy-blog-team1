package com.econo.hackday.contentsproxyblog.utils;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownParserTest {

	@Test
	public void convertTest() {
		String html = MarkdownParser.convert("This is *Sparta*");
		assertThat(html).isEqualTo("<p>This is <em>Sparta</em></p>\n");
	}

	@Test
	public void convertTest_2() {
		String html = MarkdownParser.convert("hello\nworld");
		assertThat(html).isEqualTo("<p>hello<br/>world</p>\n");
	}

	@Test
	public void imageTest() throws IOException {
		String uri = "https://github.com/JNU-econovation/markdown-study/blob/master/readme.md";
		System.out.println(GithubMarkdownLoader.getContents(uri));

		String html = MarkdownParser.convert("![econovation](picture/economark.jpg)");
		System.out.println(html);
	}
}