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
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
                           "id",
                           "tags",
                           "description",
                           "name",
                           "keyword",
                           "line",
                           "steps",
                           "examples",
                           "type"
                   })

@Document(collection = "scenario")
public class Scenario
{

    public static final String PROJECTID = "projectid";
    public static final String FEATUREID = "featureid";
    public static final String FEATURENAME = "featurename";
    public static final String APPROVED = "approved";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String NUMBER = "number";
    public static final String REVIEWCOMMENTS = "reviewcomments";

    @JsonProperty("id")
    @Field(ID)
    private String id;

    @JsonProperty("tags")
    @Field("tags")
    private List<Tag> tags = new ArrayList<Tag>();

    @JsonProperty("description")
    @Field("description")
    private String description;

    @JsonProperty("name")
    @Field(NAME)
    private String name;

    @JsonProperty("keyword")
    @Field("keyword")
    private String keyword;

    @JsonProperty("line")
    @Field("line")
    private Integer line;

    @JsonProperty("steps")
    @Field("steps")
    private List<Step> steps = new ArrayList<Step>();

    @JsonProperty("examples")
    @Field("examples")
    private List<Example> examples = new ArrayList<Example>();

    @JsonProperty("type")
    @Field("type")
    private String type;

    @JsonProperty("comments")
    @Field("comments")
    private List<Comment> comments = new ArrayList<Comment>();


    @Field(REVIEWCOMMENTS)
    private List<String> reviewComments = new ArrayList<String>();

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Field(APPROVED)
    private Boolean approved = false;

    @Field(FEATUREID)
    private String featureId;

    @Field(PROJECTID)
    private String projectId;

    @Field(FEATURENAME)
    private String featureName;

    @Field(NUMBER)
    private Integer number;

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

    public List<String> getReviewComments()
    {
        return reviewComments;
    }

    public void setReviewComments(List<String> reviewComments)
    {
        this.reviewComments = reviewComments;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
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

    @JsonProperty("steps")
    public List<Step> getSteps()
    {
        return steps;
    }

    @JsonProperty("steps")
    public void setSteps(List<Step> steps)
    {
        this.steps = steps;
    }

    @JsonProperty("examples")
    public List<Example> getExamples()
    {
        return examples;
    }

    @JsonProperty("examples")
    public void setExamples(List<Example> examples)
    {
        this.examples = examples;
    }

    @JsonProperty("type")
    public String getType()
    {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type)
    {
        this.type = type;
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

    public Boolean getApproved()
    {
        return approved;
    }

    public void setApproved(Boolean _isApproved)
    {
        this.approved = _isApproved;
    }

    public String getFeatureId()
    {
        return featureId;
    }

    public void setFeatureId(String featureId)
    {
        this.featureId = featureId;
    }

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public String getFeatureName()
    {
        return featureName;
    }

    public void setFeatureName(String featureName)
    {
        this.featureName = featureName;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public Boolean compareTo(Scenario scenario)
    {

        if (this.steps.size() != scenario.steps.size())
            return false;

        if (this.examples.size() != scenario.examples.size())
            return false;

        for (int index = 0; index < steps.size(); index++)
            if (!steps.get(index).compareTo(scenario.steps.get(index)))
                return false;

        for (int index = 0; index < examples.size(); index++)
            if (!examples.get(index).compareTo(scenario.examples.get(index)))
                return false;

        return true;

    }

    public int getTotalScenariosFromExampleTable()
    {

        int totalExamples = 0;

        for (Example example : examples)
        {
            totalExamples += (example.getRows().size() - 1);
        }

        if (totalExamples == 0)
            return 1;
        else
            return totalExamples;
    }
}