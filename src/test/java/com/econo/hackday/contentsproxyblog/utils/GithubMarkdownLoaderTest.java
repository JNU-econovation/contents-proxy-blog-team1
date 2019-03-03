package com.econo.hackday.contentsproxyblog.utils;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GithubMarkdownLoaderTest {

	@Test
	public void getContentsTest() throws IOException {
		String uri = "https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/master/README.md";
		String answer = "## 기술 스택\n" +
				"- Java 11\n" +
				"- Web tier : Spring Boot (+ Spring Web MVC)\n" +
				"- Perstence layer : JPA (Hibernate)\n" +
				"- github 연동 : [egit-github](https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core)\n" +
				"- markdown -> html 렌더링\n" +
				"    - 서버에서 할 경우 : [flexmark-java](https://github.com/vsch/flexmark-java)\n" +
				"    - 클라이언트에서 할 경우 : [markdown-it](https://github.com/markdown-it/markdown-it)\n" +
				"- DB : H2db\n\n";

		assertThat(GithubMarkdownLoader.getContents(uri)).contains(answer);
	}

	@Test
	public void getFilePathTest() {
		String uri = "https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/master/README.md";

		assertThat(GithubMarkdownLoader.getFilePath(uri)).isEqualTo("/README.md");
	}

	@Test
	public void getInfoPath() {
		String uri = "https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/master/README.md";
		String expectedInfoPath = "https://github.com/JNU-econovation/contents-proxy-blog-team1";
		assertThat(GithubMarkdownLoader.getInfoPath(uri)).isEqualTo(expectedInfoPath);
	}

}