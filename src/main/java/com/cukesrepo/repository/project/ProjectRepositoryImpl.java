package com.cukesrepo.repository.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.google.common.base.Optional;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class ProjectRepositoryImpl implements ProjectRepository
{

    private final MongoTemplate _mongoTemplate;
    private List<Project> _projects = new ArrayList<Project>();

    private static final Logger LOG = LoggerFactory.getLogger(ProjectRepository.class);

    @Autowired
    public ProjectRepositoryImpl(MongoTemplate mongoTemplate)
    {

        Validate.notNull(mongoTemplate, "mongoTemplate cannot be null");

        _mongoTemplate = mongoTemplate;
    }

    public List<Project> getProjects()
    {

        LOG.info("Querying db to get all the projects");

        _projects = _mongoTemplate.find(new Query(), Project.class);

        return _projects;
    }

    @Override
    public void addProject(Map<String, String[]> parameterMap)
    {

        Project project = new Project();
        project.setName(parameterMap.get("displayprojectname")[0]);
        project.setRepositoryPath(parameterMap.get("repositorypath")[0]);
        project.setFeaturesPath(parameterMap.get("featurespath")[0]);
        project.setEmailPo(parameterMap.get("emailofpo")[0]);
        project.setId((parameterMap.get("projectname")[0]).replaceAll("[^\\w]", "").toLowerCase());

        LOG.info("Adding project '{}'", project.getName());

        _mongoTemplate.findAndRemove(new Query(Criteria.where(Project.NAME).is(parameterMap.get("projectname")[0])), Project.class);
        _mongoTemplate.insert(project);

    }

    @Override
    public Optional<Project> getProjectById(String projectId) throws ProjectNotFoundException
    {

        Query query = new Query(Criteria.where(Project.ID).is(projectId));

        Project project = _mongoTemplate.findOne(query, Project.class);

        LOG.info("Project '{}' found from db", projectId);

        Optional<Project> projectOptional = Optional.fromNullable(project);

        if (projectOptional.isPresent())
            return projectOptional;

        throw new ProjectNotFoundException("Project '" + projectId + "' not found in db");
    }

    @Override
    public Optional<Project> getProjectByName(String projectName) throws ProjectNotFoundException
    {

        Query query = new Query(Criteria.where(Project.NAME).is(projectName));

        Project project = _mongoTemplate.findOne(query, Project.class);

        LOG.info("Project '{}' found from db", projectName);

        Optional<Project> projectOptional = Optional.fromNullable(project);

        if (projectOptional.isPresent())
            return projectOptional;

        throw new ProjectNotFoundException("Project '" + projectName + "' not found in db");
    }

    @Override
    public void updateProject(String projectId, Map<String, String[]> parameterMap)
    {

        LOG.info("Update project with '{}'", parameterMap);

        Project project = new Project();
        project.setName(parameterMap.get("projectname")[0]);
        project.setRepositoryPath(parameterMap.get("repositorypath")[0]);
        project.setFeaturesPath(parameterMap.get("featurespath")[0]);
        project.setEmailPo(parameterMap.get("emailofpo")[0]);

        project.setId(projectId);

        _mongoTemplate.findAndRemove(new Query(Criteria.where(Project.ID).is(projectId)), Project.class);

        _mongoTemplate.insert(project);
    }

    @Override
    public void deleteProject(String projectId)
    {

        LOG.info("Delete entire project '{}'", projectId);

        _mongoTemplate.findAndRemove(new Query(Criteria.where(Project.ID).is(projectId)), Project.class);
    }

}
