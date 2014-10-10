package com.cukesrepo.controller;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.cukesrepo.exceptions.FeatureNotFoundException;
import com.cukesrepo.page.DiscussionPage;
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
public class DiscussionController
{

    private final FeatureService _featureService;
    private final ProjectService _projectService;
    private final ScenarioService _scenarioService;


    @Autowired
    public DiscussionController
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

    @RequestMapping(value = {"projects/{projectId}/{featureId}/discuss"}, method = RequestMethod.GET)
    @ResponseBody
    public void renderDiscussion(HtmlCanvas html, @PathVariable String projectId, @PathVariable String featureId) throws IOException
    {
        html.render(new DiscussionPage(_projectService, _featureService, _scenarioService, projectId, featureId));
    }

    @RequestMapping(value = {"/{projectId}/{featureId}/save-discussion/persist"}, method = RequestMethod.POST)
    @ResponseBody
    protected void saveDiscussion
            (
                    HttpServletRequest request,
                    @PathVariable String projectId,
                    @PathVariable String featureId
            )
    {
        String discussions = request.getParameter("discussions");
        System.out.println("\n\nsave discussion \n\n" + projectId + " : " + featureId + " : " + discussions);

        try
        {
            _featureService.addDiscussion(projectId, featureId, discussions);
        }
        catch (FeatureNotFoundException e)
        {
            throw new RuntimeException(e);
        }

    }


}
