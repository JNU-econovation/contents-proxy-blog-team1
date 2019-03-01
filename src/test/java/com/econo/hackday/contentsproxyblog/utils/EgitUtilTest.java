package com.econo.hackday.contentsproxyblog.utils;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.ContentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class EgitUtilTest {

    EgitUtil egitUtil;

    public EgitUtilTest(){
        egitUtil = new EgitUtil();
    }

    @Test
    public void getRepositoryTest() throws IOException {
        //https://github.com/JNU-econovation/contents-proxy-blog-team1/blob/master/README.md
        Repository repository = egitUtil.repositoryService.getRepository("JNU-econovation","contents-proxy-blog-team1");
        System.out.println(repository.getGitUrl());
        System.out.println(repository.getHomepage());
        ContentsService contentsService = new ContentsService();
    }
}