package com.cukesrepo.domain;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "cells",
        "line"
})
public class Row {

    @JsonProperty("id")
    private String id;
    @JsonProperty("cells")
    private List<String> cells = new ArrayList<String>();
    @JsonProperty("line")
    private Integer line;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("cells")
    public List<String> getCells() {
        return cells;
    }

    @JsonProperty("cells")
    public void setCells(List<String> cells) {
        this.cells = cells;
    }

    @JsonProperty("line")
    public Integer getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(Integer line) {
        this.line = line;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Boolean compareTo(Row row) {
        if (cells.size() != row.cells.size())
            return false;

        for (int index = 0; index < cells.size(); index++)
            if (!cells.get(index).equals(row.cells.get(index)))
                return false;

        return true;
    }
}