package com.cukesrepo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "project")
public class Project
{

    public static final String NAME = "name";
    public static final String LAST_UPDATED = "last_updated";
    public static String ID = "_id";

    @Field(NAME)
    private String name;

    @Field("pathtofeatures")
    private String featuresPath;

    @Field("repositorypath")
    private String repositoryPath;

    @Field("email_of_po")
    private String emailPo;

    @Field("collaborators")
    private String collaborators;

    @Id
    private String id;

    @Field(LAST_UPDATED)
    private String lastUpdated;

    public String getP1TestJob()
    {
        return _p1TestJob;
    }

    public String getAcceptance()
    {
        return _acceptance;
    }

    public String getE2e()
    {
        return _e2e;
    }

    @Field("p1TestJob")
    private String _p1TestJob;

    @Field("acceptancetestjob")
    private String _acceptance;

    @Field("e2etestjob")
    private String _e2e;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRepositoryPath()
    {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath)
    {
        this.repositoryPath = repositoryPath;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public String getEmailPo()
    {
        return emailPo;
    }

    public void setEmailPo(String emailPo)
    {
        this.emailPo = emailPo;
    }

    public void setCollaborators(String collaborators)
    {
        this.collaborators = collaborators;
    }

    public String getCollaborators()
    {
        return collaborators;
    }

    public void setLastUpdated(String lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdated()
    {
        return lastUpdated;
    }

    public void setP1TestJob(String p1TestJob)
    {
        _p1TestJob = p1TestJob;
    }

    public void setAcceptanceTestJob(String acceptance)
    {
        _acceptance = acceptance;
    }

    public void setE2eTestJob(String e2e)
    {
        _e2e = e2e;
    }
}