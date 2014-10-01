package com.cukesrepo.service.project;

import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.ProjectNotFoundException;

import java.util.List;
import java.util.Map;


public interface ProjectService {

    public List<Project> getProjects();

    public void addProject(Map<String, String[]> parameterMap);

    public Project getProjectById(String projectId) throws ProjectNotFoundException;

    public void updateProject(String projectId, Map<String, String[]> parameterMap);

    public void deleteProject(String projectId);
}
