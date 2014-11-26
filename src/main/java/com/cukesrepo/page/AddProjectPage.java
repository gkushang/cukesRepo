package com.cukesrepo.page;


import java.io.IOException;

import com.cukesrepo.service.project.ProjectService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.*;


public class AddProjectPage extends HeaderFooter implements Renderable
{

    public ProjectService _projectService;

    public AddProjectPage(ProjectService projectService)
    {

        Validate.notNull(projectService, "projectService cannot be null");

        _projectService = projectService;

    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {

        addScriptsAndStyleSheets(html);

        renderHeader(html, "addProjectPage");

        html.html()
                .body();

        html.br();
        html.br();
        html.div(class_("add-project-div-title"));
        html.h2().span(id("update-add-title")).content("add project").br().br();


        html.input(type("text").class_("add-project project-name").add("placeholder", "Git Project Name")).br().br();
        html.input(type("text").class_("add-project display-name").value("").add("placeholder", "Display Project Name")).br().br();
        html.input(type("text").class_("add-project repository-path").add("placeholder", "Github SSH Clone URL")).br().br();
        html.input(type("text").class_("add-project collaborators").add("placeholder", "Add Collaborators")).br().br();
        html.input(type("text").class_("add-project project-owners").add("placeholder", "PO email address")).br().br();

        html.a(href("#").id("add-project-navigate"));
        html.input(type("button").class_("cukes-button").id("add-project").style("float: left;")
                           .value("Add"))._a();

        html._h2();
        html._div();


        html._body()
                ._html();

    }
}
