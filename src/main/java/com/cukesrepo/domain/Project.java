package com.cukesrepo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "project")
public class Project
{

    public static final String NAME = "name";
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

    public String getFeaturesPath()
    {
        return featuresPath;
    }

    public void setFeaturesPath(String featuresPath)
    {
        this.featuresPath = featuresPath;
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
}