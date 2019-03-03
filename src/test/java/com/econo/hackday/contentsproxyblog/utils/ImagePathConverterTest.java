package com.econo.hackday.contentsproxyblog.utils;

import org.junit.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;


public class ImagePathConverterTest {

	@Test
	public void hasRelativeImageTest() {
		//![Alt text](/path/to/img.jpg)
		String imageMarkdown = "212" +
				"![Alt text](/path/to/imlg.jpg)\n" +
				"![Alt text](/path/to/imdg.jpg)\n"+
				"![Alt text](/path/to/ismg.jpg)\n"+
				"![Alt text](/path/to/imgx.jpg)\n"+
				"ekf dekdenasdw";
		assertThat(ImagePathConverter.hasImage(imageMarkdown))
				.isEqualTo(true);
	}

	@Test
	public void isRelativeImageTest() throws MalformedURLException {
		String uri = "https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/master/README.md";
		assertThat(ImagePathConverter.isRelativeImage(uri)).isEqualTo(false);

		String uri1 = "/JNU-econovation/contents-proxy-blog-team1/blob/master/README.md";
		assertThat(ImagePathConverter.isRelativeImage(uri1)).isEqualTo(true);
	}

	@Test
	public void convertRlativeToAbsolute() {
		String uri = "https://github.com/JNU-econovation/markdown-study/blob/master/picture/economark.jpg?raw=true";

		String imageMarkdown = "212" +
				"![Alt text](path/to/imgx.jpg)";
		assertThat(ImagePathConverter.convertRlativeUrisToAbsolute(imageMarkdown, GithubMarkdownLoader.getInfoPath(uri)))
				.isEqualTo("212![Alt text](https://github.com/JNU-econovation/markdown-study/blob/master/path/to/imgx.jpg?raw=true)");
	}
}