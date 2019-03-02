package com.econo.hackday.contentsproxyblog.utils;

import org.eclipse.egit.github.core.RepositoryId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.egit.github.core.RepositoryId.createFromUrl;

@RunWith(SpringRunner.class)
public class GithubMarkdownLoaderTest {

    GithubMarkdownLoader githubMarkdownLoader;

    public GithubMarkdownLoaderTest() {
        githubMarkdownLoader = new GithubMarkdownLoader();
    }

    @Test
    public void getContentsTest() throws IOException {
        String uri = "https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/README.md";
        String answer = "## 기술 스택\n" +
                "- Java 11\n" +
                "- Web tier : Spring Boot (+ Spring Web MVC)\n" +
                "- Perstence layer : JPA (Hibernate)\n" +
                "- github 연동 : [egit-github](https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core)\n" +
                "- markdown -> html 렌더링\n" +
                "    - 서버에서 할 경우 : [flexmark-java](https://github.com/vsch/flexmark-java)\n" +
                "    - 클라이언트에서 할 경우 : [markdown-it](https://github.com/markdown-it/markdown-it)\n" +
                "- DB : H2db\n\n";

        assertThat(githubMarkdownLoader.getContents(uri)).contains(answer);
    }

    @Test
    public void getFilePathTest() throws MalformedURLException {
        String uri = "https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/test/README.md";
        System.out.println("? : " + new URL(uri).getPath());
        RepositoryId repositoryId = createFromUrl(uri);
        RepositoryId repositoryId1 = createFromUrl(new URL(uri));
        System.out.println(repositoryId.getOwner());
        System.out.println(repositoryId1.getOwner());

        assertThat(githubMarkdownLoader.getFilePath(uri)).isEqualTo("/test/README.md");
    }
}