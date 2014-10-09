package com.cukesrepo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
                           "id",
                           "description",
                           "name",
                           "keyword",
                           "line",
                           "elements",
                           "uri",
                           "tags"
                   })

@Document(collection = "feature")
public class Feature
{

    public static final String PROJECTID = "projectid";
    public static final String ID = "_id";
    private static final String STATUS = "status";


    @Field(PROJECTID)
    private String projectId;

    @Field(STATUS)
    private String status = FeatureStatus.NEED_REVIEW.get();

    @Field("emailsent")
    private Boolean emailSent = false;

    public int getTotalScenarios()
    {
        return totalScenarios;
    }

    public void setTotalScenarios(int totalScenarios)
    {
        this.totalScenarios = totalScenarios;
    }

    @Field("totalscenarios")
    private int totalScenarios;

    @JsonProperty("id")
    @Field(ID)
    private String id;

    @JsonProperty("description")
    @Field("description")
    private String description;

    @Field("name")
    @JsonProperty("name")
    private String name;

    @JsonProperty("keyword")
    @Field("keyword")
    private String keyword;

    @JsonProperty("line")
    private Integer line;

    @JsonProperty("elements")
    @Transient
    private List<Scenario> scenarios = new ArrayList<Scenario>();

    @JsonProperty("uri")
    private String uri;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tags")
    private List<Tag> tags = new ArrayList<Tag>();

    @JsonProperty("id")
    public String getId()
    {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id)
    {
        this.id = id;
    }

    @JsonProperty("description")
    public String getDescription()
    {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description)
    {
        this.description = description;
    }

    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }

    @JsonProperty("keyword")
    public String getKeyword()
    {
        return keyword;
    }

    @JsonProperty("keyword")
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    @JsonProperty("line")
    public Integer getLine()
    {
        return line;
    }

    @JsonProperty("line")
    public void setLine(Integer line)
    {
        this.line = line;
    }

    @JsonProperty("elements")
    public List<Scenario> getScenarios()
    {
        return scenarios;
    }

    @JsonProperty("elements")
    public void setScenarios(List<Scenario> scenarios)
    {
        this.scenarios = scenarios;
    }

    @JsonProperty("uri")
    public String getUri()
    {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }


    @JsonProperty("tags")
    public List<Tag> getTags()
    {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public Boolean getEmailSent()
    {
        return emailSent;
    }

    public void setEmailSent(Boolean emailSent)
    {
        this.emailSent = emailSent;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

}