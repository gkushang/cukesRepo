package com.cukesrepo.page;

import java.io.IOException;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.service.feature.FeatureService;
import com.cukesrepo.service.project.ProjectService;
import com.cukesrepo.service.scenario.ScenarioService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.type;


public class DiscussionPage extends HeaderFooter implements Renderable
{

    private final ProjectService _projectService;
    private final FeatureService _featureService;
    private final ScenarioService _scenarioService;
    private final String _projectId;
    private final String _featureId;

    public DiscussionPage(ProjectService projectService, FeatureService featureService,
                          ScenarioService scenarioService, String projectId, String featureId)
    {

        Validate.notNull(projectService, "projectService cannot be null");
        Validate.notNull(featureService, "featureService cannot be null");
        Validate.notNull(scenarioService, "scenarioService cannot be null");
        Validate.notEmpty(projectId, "projectId cannot be empty or null");
        Validate.notEmpty(featureId, "featureId cannot be empty or null");


        _projectService = projectService;
        _featureService = featureService;
        _scenarioService = scenarioService;
        _projectId = projectId;
        _featureId = featureId;

    }


    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {

        addScriptsAndStyleSheets(html);

        try
        {
            Feature feature = _featureService.getFeatureId(_projectId, _featureId).get();
            renderDiscussionHeader(html, _projectService.getProjectById(_projectId),
                                   feature);

            html.html()
                    .body();

            html.br();

            html.div(class_("discussion-div")).div(class_("feature_title").class_("background-color-cukes")).
                    content("Feature: " + _featureService.getFeatureId(_projectId, _featureId).get().getName());


            html.input(type("button").class_("discussion-board-button").id("cancel-discussion").value("Cancel"))
                    .input(type("hidden").id("projectId").value(_projectId))
                    .input(type("hidden").id("featureId").value(_featureId));

            html.input(type("button").class_("discussion-board-button").id("save-discussion").value("Save"))
                    .input(type("hidden").class_("projectId").value(_projectId))
                    .input(type("hidden").class_("featureId").value(_featureId));

            html.textarea(class_("discussion-text-area").id("discussions"))
                    .content(feature.getDiscussion())._div();

            html._body()
                    ._html();

        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }
}
