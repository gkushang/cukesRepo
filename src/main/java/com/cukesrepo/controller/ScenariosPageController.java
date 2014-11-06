package com.cukesrepo.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.cukesrepo.exceptions.ScenariosNotFoundException;
import com.cukesrepo.page.ScenariosPage;
import com.cukesrepo.service.feature.FeatureService;
import com.cukesrepo.service.project.ProjectService;
import com.cukesrepo.service.scenario.ScenarioService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ScenariosPageController
{


    private final ScenarioService _scenarioService;
    private final FeatureService _featureService;
    private final ProjectService _projectService;

    @Autowired
    public ScenariosPageController
            (
                    ProjectService projectService,
                    FeatureService featureService,
                    ScenarioService scenarioService
            )
    {

        Validate.notNull(projectService, "projectService cannot be null");
        Validate.notNull(featureService, "featureService cannot be null");
        Validate.notNull(scenarioService, "scenarioService cannot be null");

        _projectService = projectService;
        _featureService = featureService;
        _scenarioService = scenarioService;
    }

    @RequestMapping(value = "projects/{projectId}/{featureId}/")
    @ResponseBody
    public void renderScenariosPage
            (
                    HtmlCanvas html,
                    @PathVariable String projectId,
                    @PathVariable String featureId
            ) throws IOException
    {


        html.render
                (
                        new ScenariosPage
                                (
                                        _projectService,
                                        _featureService,
                                        _scenarioService,
                                        projectId,
                                        featureId
                                )
                );


    }


    @RequestMapping(value = {"/{projectId}/{featureId}/{scenarioNumber}/approve"}, method = RequestMethod.POST)
    @ResponseBody
    protected void approveScenario
            (
                    @PathVariable String projectId,
                    @PathVariable String featureId,
                    @PathVariable String scenarioNumber
            )
    {

        try
        {

            _scenarioService.approveScenario(projectId, featureId, scenarioNumber);

        }
        catch (ScenariosNotFoundException e)
        {
            throw new RuntimeException("Scenario not found. Replace this with rendering error page: ", e);
        }
    }

    @RequestMapping(value = {"/{projectId}/{featureId}/{scenarioNumber}/add-comment"}, method = RequestMethod.POST)
    @ResponseBody
    protected void commentOnScenario
            (
                    HttpServletRequest request,
                    @PathVariable String projectId,
                    @PathVariable String featureId,
                    @PathVariable String scenarioNumber
            )
    {


        try
        {

            String comments = request.getParameter("comments");

            if (comments != null && !comments.isEmpty())
            {
                _scenarioService.addComment(projectId, featureId, scenarioNumber, comments);

            }


        }
        catch (ScenariosNotFoundException e)
        {
            throw new RuntimeException("Scenario not found. Replace this with rendering error page: ", e);
        }
    }
}