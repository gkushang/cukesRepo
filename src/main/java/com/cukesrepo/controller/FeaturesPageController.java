package com.cukesrepo.controller;


import java.io.IOException;

import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.page.FeaturesPage;
import com.cukesrepo.service.feature.FeatureService;
import com.cukesrepo.service.project.ProjectService;
import com.cukesrepo.service.scenario.ScenarioService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FeaturesPageController {

    private final ScenarioService _scenarioService;
    private final FeatureService _featureService;
    private final ProjectService _projectService;


    @Autowired
    public FeaturesPageController(FeatureService featureService, ProjectService projectService, ScenarioService scenarioService) {

        Validate.notNull(featureService, "featureService cannot be null");

        _featureService = featureService;
        _projectService = projectService;
        _scenarioService = scenarioService;

    }

    @RequestMapping(value = {"/projects/{projectId}/"})
    @ResponseBody
    public void renderFeaturesPage
            (
                    HtmlCanvas html,
                    @PathVariable String projectId

            ) throws IOException, ProjectNotFoundException {

        html.render(new FeaturesPage
                (
                        _featureService,
                        _scenarioService,
                        _projectService.getProjectById(projectId)
                ));
    }


}