package com.cukesrepo.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.cukesrepo.page.AddRequestProjectPage;
import com.cukesrepo.page.ProjectsPage;
import com.cukesrepo.page.UpdateProjectPage;
import com.cukesrepo.service.project.ProjectService;
import org.rendersnake.HtmlCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProjectsPageController
{

    private final ProjectService _projectService;
    private final String _isAddProjectEnabled;

    @Autowired
    public ProjectsPageController
            (
                    ProjectService projectService,
                    @Value("${add.project.enable}") String isAddProjectEnabled
            )
    {

        _projectService = projectService;
        _isAddProjectEnabled = isAddProjectEnabled;
    }

    @RequestMapping(value = {"/"})
    @ResponseBody
    public void redirectto
            (
                    HttpServletResponse response
            ) throws IOException
    {
        response.sendRedirect("/projects/");
    }

    @RequestMapping(value = {"/projects/"})
    @ResponseBody
    public void renderProjectsPage
            (
                    HtmlCanvas html
            ) throws IOException
    {

        html.render(new ProjectsPage(_projectService, _isAddProjectEnabled));

    }

    @RequestMapping(value = {"/submit/add-project-request"})
    @ResponseBody
    public void renderRequestProjectsPage(HtmlCanvas html) throws IOException
    {

        html.render(new AddRequestProjectPage(_projectService, true));

    }


    @RequestMapping(value = {"/user/kushpassword/add-project"})
    @ResponseBody
    public void renderAddProjectsPage(HtmlCanvas html) throws IOException
    {

        html.render(new AddRequestProjectPage(_projectService, false));

    }

    @RequestMapping(value = {"/projects/{projectId}/settings"})
    @ResponseBody
    public void renderUpdateProjectsPage
            (
                    HtmlCanvas html,
                    @PathVariable String projectId

            ) throws IOException
    {

        html.render(new UpdateProjectPage(_projectService, projectId, _isAddProjectEnabled));

    }
}

