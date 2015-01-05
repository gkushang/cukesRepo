package com.cukesrepo.page;


import java.io.IOException;

import com.cukesrepo.service.project.ProjectService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.*;


public class AddRequestProjectPage extends HeaderFooter implements Renderable
{

    private final Boolean _isRequestToAdd;
    public ProjectService _projectService;

    public AddRequestProjectPage(ProjectService projectService, Boolean isRequestToAdd)
    {

        Validate.notNull(projectService, "projectService cannot be null");

        _projectService = projectService;
        _isRequestToAdd = isRequestToAdd;

    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {
        addScriptsAndStyleSheets(html);

        renderHeader(html, "updateProjectPage");

        html.html()
                .body();

        html.br();
        html.br();
        html.div(class_("add-project-div-title"));
        html.h2().span(id("update-add-title")).content("add project").br();

        html.span(id("update-error")).content("").br();

        html.span(id("update-elements-name")).content("Project");
        html.input(type("text").class_("add-project project-name").add("placeholder", "GitHub Project Name")).br();

        html.span(id("update-elements-name")).content("Git Repository");
        html.input(type("text").class_("add-project repository-path").add("placeholder", "GitHub SSH Clone URL")).br();

        html.span(id("update-elements-name")).content("Collaborators");
        html.input(type("text").class_("add-project collaborators").add("placeholder", "Add Collaborators email addresses")).br();

        html.span(id("update-elements-name")).content("PO");
        html.input(type("text").class_("add-project project-owners").add("placeholder", "PO email address")).br().br();

        html.a(href("#").id("add-project-navigate"));

        String btnId = "add-project";

        if (_isRequestToAdd)
        {
            btnId = "request-project";
        }

        html
                .input(type("button").class_("cukes-button").id(btnId).style("float: right;")
                               .value("Add"))

                .a(class_("").href("/projects/")).input(type("button").class_("cukes-button").id("cancel-update").style("float: left;")
                                                                .value("Cancel"))._a()
                .br().br();


        html._a();

        html.span(id("confirmation")).content("").br();


        html._h2();
        html._div();

        html._body()
                ._html();
    }
}
