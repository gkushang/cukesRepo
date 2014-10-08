package com.cukesrepo.service.project;

import java.util.List;
import java.util.Map;

import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.repository.project.ProjectRepository;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectServiceImpl implements ProjectService
{

    private final ProjectRepository _projectRepository;

    @Autowired
    public ProjectServiceImpl
            (
                    ProjectRepository projectRepository
            )
    {

        Validate.notNull(projectRepository, "projectRepository cannot be null");

        _projectRepository = projectRepository;
    }

    @Override
    public List<Project> getProjects()
    {

        return _projectRepository.getProjects();
    }

    @Override
    public void addProject(Map<String, String[]> parameterMap)
    {
        _projectRepository.addProject(parameterMap);

    }

    @Override
    public Project getProjectById(String projectId) throws ProjectNotFoundException
    {
        return _projectRepository.getProjectById(projectId).get();
    }

    @Override
    public Project getProjectByName(String projectName) throws ProjectNotFoundException
    {
        return _projectRepository.getProjectById(projectName).get();
    }

    @Override
    public void updateProject(String projectId, Map<String, String[]> parameterMap)
    {
        _projectRepository.updateProject(projectId, parameterMap);
    }

    @Override
    public void deleteProject(String projectId)
    {
        _projectRepository.deleteProject(projectId);
    }
}
