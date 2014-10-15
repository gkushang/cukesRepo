package com.cukesrepo.page;


import java.io.IOException;

import com.cukesrepo.domain.Project;
import com.cukesrepo.service.project.ProjectService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.*;


public class ProjectsPage extends HeaderFooter implements Renderable
{

    public ProjectService _projectService;

    public ProjectsPage(ProjectService projectService)
    {

        Validate.notNull(projectService, "projectService cannot be null");

        _projectService = projectService;

    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {

        addScriptsAndStyleSheets(html);

        renderHeader(html);

        html.html()
                .body(class_("background-color-cukes"));

        html.br();
        html.br();

        html.div(class_("projects-div-title").class_("background-color-cukes"));
        html.h3().span(id("project-title")).content("projects");

        html.a(href("/user/add-project"));
        html.input(type("button").class_("cukes-button").id("add-new-project")
                           .value("Add Project").style("float: right;"))._a();


        html._h3();


        for (Project project : _projectService.getProjects())
        {
            html.div(class_("project-links-div"));
            html.h1()
                    .a(href("/projects/" + project.getId() + "/settings")).img(class_("settings-icon-image"))
                    .a(href(project.getId() + "/").class_("projects-a-links").id("no-decoration")).content(project.getName().toLowerCase())
                    ._a()
                    ._h1();
            html._div();
        }

        html._div();
        html._body()
                ._html();


    }
}
