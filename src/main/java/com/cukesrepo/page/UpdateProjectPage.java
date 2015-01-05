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

            renderHeader(html, "updateProjectPage");

            html.html()
                    .body();

            html.br();
            html.br();
            html.div(class_("add-project-div-title"));
            html.h2().span(id("update-add-title")).content("update project").br();

            html.span(id("update-error")).content("").br();

            html.span(id("update-elements-name")).content("Project");
            html.input(type("text").class_("add-project project-name").value(project.getId()).add("placeholder", "Git Project Name").disabled("")).br();

            html.span(id("update-elements-name")).content("Project Name");
            html.input(type("text").class_("add-project display-name").value(project.getName()).add("placeholder", "Display Project Name")).br();

            html.span(id("update-elements-name")).content("Git Repository");
            html.input(type("text").class_("add-project repository-path").value(project.getRepositoryPath()).add("placeholder", "Github SSH Clone URL")).br();

//            html.span(id("update-elements-name")).content("Path To Features");
//            html.input(type("text").class_("add-project git-branch").value("").add("placeholder", "Path to Features folder (path/to/features)")).br();

            html.span(id("update-elements-name")).content("Collaborators");
            html.input(type("text").class_("add-project collaborators").value(project.getCollaborators()).add("placeholder", "Add Collaborators")).br();

            html.span(id("update-elements-name")).content("PO");
            html.input(type("text").class_("add-project project-owners").value(project.getEmailPo()).add("placeholder", "PO email address")).br().br();

            html.br();
            html.div(class_("fusion-test-job-div"));
            html.div(class_("fusion-test-job-title")).span(id("test-job-title")).content("Test Reports")._div().br();

            html.span(id("update-elements-name")).content("P1 Test");
            html.input(type("text").class_("add-project p1-test-job").value(project.getP1TestJob()).add("placeholder", "path to P1 Test report")).br();

            html.span(id("update-elements-name")).content("Acceptance Test");
            html.input(type("text").class_("add-project acceptance-test-job").value(project.getAcceptance()).add("placeholder", "path to Acceptance Test report")).br();

            html.span(id("update-elements-name")).content("End to End Test");
            html.input(type("text").class_("add-project e2e-test-job").value(project.getE2e()).add("placeholder", "path to E2E Test report")).br();

            html._div();

            html.a(href("#").id("add-project-navigate"));
            html
                    .input(type("button").class_("cukes-button").id("update-project").style("float: right;")
                                   .value("Save"))
                    .input(type("hidden").id("project-id").value(_projectId))

                    .a(class_("").href("/projects/")).input(type("button").class_("cukes-button").id("cancel-update").style("float: left;")
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
