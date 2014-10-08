package com.cukesrepo.domain;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
                           "value",
                           "line"
                   })
public class Comment
{

    @JsonProperty("value")
    private String value;
    @JsonProperty("line")
    private Integer line;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("value")
    public String getValue()
    {
        return value;
    }

    @JsonProperty("value")
    public void setName(String value)
    {
        this.value = value;
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
}