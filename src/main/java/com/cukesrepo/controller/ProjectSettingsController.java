package com.cukesrepo.controller;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cukesrepo.service.feature.FeatureService;
import com.cukesrepo.service.project.ProjectService;
import com.cukesrepo.service.scenario.ScenarioService;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProjectSettingsController
{

    private final ScenarioService _scenarioService;
    private final FeatureService _featureService;
    private final ProjectService _projectService;


    @Autowired
    public ProjectSettingsController
            (
                    FeatureService featureService,
                    ProjectService projectService,
                    ScenarioService scenarioService
            )
    {

        Validate.notNull(featureService, "featureService cannot be null");
        Validate.notNull(projectService, "projectService cannot be null");
        Validate.notNull(scenarioService, "scenarioService cannot be null");

        _featureService = featureService;
        _projectService = projectService;
        _scenarioService = scenarioService;

    }


    @RequestMapping(value = {"/user/add-project/persist"})
    @ResponseBody
    protected void persistProject(HttpServletRequest request) throws IOException
    {

        _projectService.addProject(request.getParameterMap());

    }


    @RequestMapping(value = {"/projects/{projectId}/update"})
    @ResponseBody
    protected void updateProject
            (
                    @PathVariable String projectId,
                    HttpServletRequest request,
                    HttpServletResponse response

            ) throws IOException
    {


        try
        {
            _projectService.updateProject(projectId, request.getParameterMap());
        }
        catch (IllegalArgumentException e)
        {
            response.reset();
            response.setContentType("text/plain");
            response.getWriter().write(e.getMessage());
        }
    }

    @RequestMapping(value = {"/projects/{projectId}/delete"})
    @ResponseBody
    protected void deleteProject
            (
                    @PathVariable String projectId

            ) throws IOException
    {

        _projectService.deleteProject(projectId);
        _featureService.deleteFeatures(projectId);
        _scenarioService.deleteScenarios(projectId);
    }

}
