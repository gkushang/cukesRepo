package com.cukesrepo.controller;

import com.cukesrepo.page.AddProjectPage;
import com.cukesrepo.page.ProjectsPage;
import com.cukesrepo.page.UpdateProjectPage;
import com.cukesrepo.service.project.ProjectService;
import org.rendersnake.HtmlCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ProjectsPageController {

    private final ProjectService _projectService;

    @Autowired
    public ProjectsPageController
            (
                    ProjectService projectService
            ) {

        _projectService = projectService;
    }

    @RequestMapping(value = {"/projects/"})
    @ResponseBody
    public void renderProjectsPage
            (
                    HtmlCanvas html
            ) throws IOException {

        html.render(new ProjectsPage(_projectService));

    }

    @RequestMapping(value = {"/user/add-project"})
    @ResponseBody
    public void renderAddProjectsPage(HtmlCanvas html) throws IOException {

        html.render(new AddProjectPage(_projectService));

    }

    @RequestMapping(value = {"/projects/{projectId}/settings"})
    @ResponseBody
    public void renderUpdateProjectsPage
            (
                    HtmlCanvas html,
                    @PathVariable String projectId

            ) throws IOException {

        html.render(new UpdateProjectPage(_projectService, projectId));

    }
}

