package com.cukesrepo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "project")
public class Project {

    public static final String NAME = "name";
    public static String ID = "_id";

    @Field(NAME)
    private String name;

    @Field("repositorypath")
    private String repositoryPath;

    @Id
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getId() {
        return this.id;
    }
}