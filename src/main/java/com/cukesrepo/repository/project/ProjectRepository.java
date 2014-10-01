package com.cukesrepo.repository.project;


import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.google.common.base.Optional;

import java.util.List;
import java.util.Map;

public interface ProjectRepository {

    public List<Project> getProjects();

    public void addProject(Map<String, String[]> parameterMap);

    public Optional<Project> getProjectById(String projectId) throws ProjectNotFoundException;

    public void updateProject(String projectId, Map<String, String[]> parameterMap);

    public void deleteProject(String projectId);
}


