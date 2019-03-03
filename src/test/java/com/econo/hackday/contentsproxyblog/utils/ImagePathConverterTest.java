package com.econo.hackday.contentsproxyblog.utils;

import com.econo.hackday.contentsproxyblog.exception.UndefinedImageExtension;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;


public class ImagePathConverterTest {

	@Test
	public void hasImageTest() {
		String imageMarkdown = "212" +
				"![Alt text](/path/to/imlg.jpg)\n" +
				"![Alt text](/path/to/imdg.jpg)\n" +
				"![Alt text](/path/to/ismg.jpg)\n" +
				"![Alt text](/path/to/imgx.jpg)\n" +
				"ekf dekdenasdw";

		assertThat(ImagePathConverter.hasImage(imageMarkdown))
				.isEqualTo(true);
	}

	@Test
	public void isRelativeImageTest() throws MalformedURLException {
		StringBuilder uri = new StringBuilder("https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/master/README.md");
		assertThat(ImagePathConverter.isRelativeImage(uri)).isEqualTo(false);

		StringBuilder uri1 = new StringBuilder("/JNU-econovation/contents-proxy-blog-team1/blob/master/README.md");
		assertThat(ImagePathConverter.isRelativeImage(uri1)).isEqualTo(true);
	}

	@Test
	public void convertRelativeToAbsolute() {
		String uri = "https://github.com/JNU-econovation/markdown-study/blob/master/picture/economark.jpg?raw=true";
		String imageMarkdown = "212![Alt text](path/to/imgx.jpg)";

		assertThat(ImagePathConverter.convertRelativeUrisToAbsolute(imageMarkdown, GithubMarkdownLoader.getInfoPath(uri)))
				.isEqualTo("212![Alt text](https://github.com/JNU-econovation/markdown-study/blob/master/path/to/imgx.jpg?raw=true)");
	}

	@Test
	public void toAbsoluteImagePathTest() {
		String infoPath = "https://github.com/JNU-econovation/markdown-study/blob/master";
		String imageMarkdown = "![Alt text](path/to/imgx.jpg)";
		String expectedAnswer = "![Alt text](https://github.com/JNU-econovation/markdown-study/blob/master/path/to/imgx.jpg?raw=true)";

		assertThat(ImagePathConverter.toAbsoluteImagePath(imageMarkdown, infoPath)).isEqualTo(expectedAnswer);
	}

	@Test
	public void 이미지_확장자_에러_테스트() {
		String infoPath = "https://github.com/JNU-econovation/markdown-study/blob/master";
		String imageMarkdown = "![Alt text](path/to/imgx.image)";
		boolean error = false;

		try {
			ImagePathConverter.toAbsoluteImagePath(imageMarkdown, infoPath);
		} catch (UndefinedImageExtension e) {
			error = true;
		}

		assertThat(error).isEqualTo(true);
	}
}