package com.econo.hackday.contentsproxyblog.utils;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.util.EncodingUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class EgitUtilTest {

    EgitUtil egitUtil;

    public EgitUtilTest() {
        egitUtil = new EgitUtil();
    }

    @Test
    public void getContextTest() throws Exception {
        RepositoryId repositoryId = new RepositoryId("dadadamarine", "Chooser");
        ContentsService contentsService = new ContentsService();
        String content = contentsService.getReadme(repositoryId).getContent();

        assertThat(new String(EncodingUtils.fromBase64(content), "UTF-8")).isEqualTo("a\n");
    }
}