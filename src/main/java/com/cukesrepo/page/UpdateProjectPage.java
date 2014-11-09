package com.cukesrepo.page;


import java.io.IOException;

import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.service.project.ProjectService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.*;


public class UpdateProjectPage extends HeaderFooter implements Renderable
{

    private final String _projectId;
    private final ProjectService _projectService;
    private final String _isAddProjectEnabled;

    public UpdateProjectPage(ProjectService projectService, String projectId,
                             String isAddProjectEnabled)

    {

        Validate.notNull(projectService, "projectService cannot be null");
        Validate.notEmpty(projectId, "projectId cannot be empty/null");

        _projectService = projectService;
        _projectId = projectId;
        _isAddProjectEnabled = isAddProjectEnabled;

    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {


        try
        {

            Project project = _projectService.getProjectById(_projectId);


            addScriptsAndStyleSheets(html);

            renderHeader(html);

            html.html()
                    .body();

            html.br();
            html.br();
            html.div(class_("add-project-div-title"));
            html.h2().span(id("update-add-title")).content("update project").br();

            html.span(id("update-error")).content("");
            html.input(type("text").class_("add-project project-name").value(project.getId()).add("placeholder", "Git Project Name").disabled("")).br().br();
            html.input(type("text").class_("add-project display-name").value(project.getName()).add("placeholder", "Display Project Name")).br().br();
            html.input(type("text").class_("add-project repository-path").value(project.getRepositoryPath()).add("placeholder", "Github SSH Clone URL")).br().br();
            html.input(type("text").class_("add-project git-branch").value(project.getFeaturesPath()).add("placeholder", "Path to Features folder (path/to/features)")).br().br();
            html.input(type("text").class_("add-project collaborators").value(project.getCollaborators()).add("placeholder", "Add Collaborators")).br().br();
            html.input(type("text").class_("add-project project-owners").value(project.getEmailPo()).add("placeholder", "PO email address")).br().br();

            html.a(href("#").id("add-project-navigate"));
            html.input(type("button").class_("cukes-button").id("update-project").style("float: left;")
                               .value("Save changes"))
                    .input(type("hidden").id("project-id").value(_projectId))
                    .a(class_("").href("/projects/")).input(type("button").class_("cukes-button").id("cancel-update").style("float: right;")
                                                                    .value("Cancel"))._a()
                    .br().br();
            if (_isAddProjectEnabled.equals("true"))
            {
                html.input(type("button").class_("project-delete-button").id("delete-project").style("float: center;")
                                   .value("Delete Project"));
            }

            html._a();

            html._h2();
            html._div();


            html._body()
                    ._html();
        }
        catch (ProjectNotFoundException e)
        {
            throw new RuntimeException("Project not found. Replace this with rendering error page: ", e);
        }

    }
}
