package com.cukesrepo.repository.project;


import java.util.List;
import java.util.Map;

import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.google.common.base.Optional;


public interface ProjectRepository
{

    public List<Project> getProjects();

    public void addProject(Map<String, String[]> parameterMap);

    public Optional<Project> getProjectById(String projectId) throws ProjectNotFoundException;

    Optional<Project> getProjectByName(String projectName) throws ProjectNotFoundException;

    public void updateProject(String projectId, Map<String, String[]> parameterMap);

    public void deleteProject(String projectId);

    void updateLastUpdatedTime(String projectName, String time) throws ProjectNotFoundException;
}


