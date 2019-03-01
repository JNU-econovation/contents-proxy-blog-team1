package com.econo.hackday.contentsproxyblog.utils;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.util.EncodingUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class EgitUtilTest {

    EgitUtil egitUtil;

    public EgitUtilTest() {
        egitUtil = new EgitUtil();
    }

    @Test
    public void 예시md파일가져오기테스트() throws Exception {
        RepositoryId repositoryId = new RepositoryId("dadadamarine", "Chooser");
        ContentsService contentsService = new ContentsService();
        String content = contentsService.getReadme(repositoryId).getContent();

        assertThat(new String(EncodingUtils.fromBase64(content), "UTF-8")).isEqualTo("a\n");
    }

    @Test
    public void 특정url파일가져오기테스트() throws Exception {
        RepositoryId repositoryId = new RepositoryId("JNU-econovation", "contents-proxy-blog-team1");
        ContentsService contentsService = new ContentsService();
        String uri2 = "README.md";

        String rawContent2=contentsService.getContents(repositoryId, uri2).get(0).getContent();
        String content = new String(EncodingUtils.fromBase64(rawContent2),"UTF-8");
        assertThat(content).contains("## 기술 스택\n" +
                "- Java 11\n" +
                "- Web tier : Spring Boot (+ Spring Web MVC)\n" +
                "- Perstence layer : JPA (Hibernate)\n" +
                "- github 연동 : [egit-github](https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core)\n" +
                "- markdown -> html 렌더링\n" +
                "    - 서버에서 할 경우 : [flexmark-java](https://github.com/vsch/flexmark-java)\n" +
                "    - 클라이언트에서 할 경우 : [markdown-it](https://github.com/markdown-it/markdown-it)\n" +
                "- DB : H2db\n\n");
    }

    @Test
    public void 특정_브런치_파일_가져오기_테스트() throws IOException {
        RepositoryId repositoryId = new RepositoryId("JNU-econovation", "contents-proxy-blog-team1");
        ContentsService contentsService = new ContentsService();
        String uri2 = "README.md";

        String rawContent2=contentsService.getContents(repositoryId, uri2, "feature/egit").get(0).getContent();
        String content = new String(EncodingUtils.fromBase64(rawContent2),"UTF-8");
        assertThat(content).contains("## 기술 스택\n" +
                "- Java 11\n" +
                "- Web tier : Spring Boot (+ Spring Web MVC)\n" +
                "- Perstence layer : JPA (Hibernate)\n" +
                "- github 연동 : [egit-github](https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core)\n" +
                "- markdown -> html 렌더링\n" +
                "    - 서버에서 할 경우 : [flexmark-java](https://github.com/vsch/flexmark-java)\n" +
                "    - 클라이언트에서 할 경우 : [markdown-it](https://github.com/markdown-it/markdown-it)\n" +
                "- DB : H2db\n\n");
    }
    @Test
    public void 주어진url의파일가져오기테스트() throws IOException {
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

        assertThat(egitUtil.getContents(uri)).contains(answer);
    }

    @Test
    public void createRepositoryId() {
        String uri = "https://github.com/owner/repository/blob/test/README.md";

        RepositoryId repositoryId = RepositoryId.create("owner","repository");
        assertThat(egitUtil.createRepositoryId(uri)).isEqualTo(repositoryId);
    }

    @Test
    public void getFilePath() {
        String uri = "https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/test/README.md";

        assertThat(egitUtil.getFilePath(uri)).isEqualTo("/test/README.md");
    }
}