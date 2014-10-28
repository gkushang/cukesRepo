package com.cukesrepo.service.git;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cukesrepo.component.GitComponent;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@EnableScheduling
public class GitService
{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final GitComponent _gitComponent;
    private static final Logger LOG = LoggerFactory.getLogger(GitService.class);

    @Autowired
    public GitService(GitComponent gitComponent)
    {
        Validate.notNull(gitComponent, "gitComponent cannot be null");

        _gitComponent = gitComponent;
    }

    @Scheduled(fixedDelay = 30000)
    public void reportCurrentTime() throws IOException, InterruptedException
    {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        _gitComponent.pullCurrentBranch();
    }
}

