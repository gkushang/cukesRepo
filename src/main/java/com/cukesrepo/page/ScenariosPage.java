package com.cukesrepo.page;

import java.io.IOException;
import java.util.List;

import com.cukesrepo.domain.Example;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.domain.Row;
import com.cukesrepo.domain.Scenario;
import com.cukesrepo.domain.Step;
import com.cukesrepo.domain.Tag;
import com.cukesrepo.exceptions.FeatureNotFoundException;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.service.feature.FeatureService;
import com.cukesrepo.service.project.ProjectService;
import com.cukesrepo.service.scenario.ScenarioService;
import com.google.common.base.Joiner;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.*;


public class ScenariosPage extends HeaderFooter implements Renderable
{

    private final String _projectId;
    private final String _featureId;
    private final FeatureService _featureService;
    private final ProjectService _projectService;
    private final ScenarioService _scenarioService;
    private List<Feature> _features;
    private Project _project;
    private Feature _feature;
    private List<Scenario> _scenarios;

    public ScenariosPage(ProjectService projectService, FeatureService featureService, ScenarioService scenarioService, String projectId, String featureId)
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


    private void _addScenarioDiv(HtmlCanvas html, Scenario scenario) throws Throwable
    {

        html.div(class_("scenario-background").id("scenario" + scenario.getNumber()));
    }

    private void _addTags(HtmlCanvas html, List<Tag> tags)
            throws Throwable
    {

        html.span(class_("ptag").class_("background-color-cukes")).content(Joiner.on("\t").join(tags));
    }


    private void _addSteps(List<Step> steps, HtmlCanvas html) throws Throwable
    {

        html.br();
        for (Step step : steps)
        {
            String complete_step = step.getKeyword() + step.getName();

            complete_step = complete_step.replaceAll("\u003c", "&lt");
            complete_step = complete_step.replaceAll("\u003e", "&gt");

            html.span(class_("step")).content(complete_step);

            List<Row> rows = step.getRows();
            if (rows.size() != 0)
            {

                html.table(class_("datatable"));
                html.tbody();
                for (int j = 0; j < rows.size(); j++)
                {
                    html.tr();
                    Row row = rows.get(j);
                    List<String> cells = row.getCells();
                    for (String cell : cells)
                        html.td().content(cell);
                    html._tr();
                }//row
                html._tbody();
                html._table();

            }
        }

    }

    private void _addTable(Example example, HtmlCanvas html) throws Throwable
    {

        html.br();
        html.span(class_("example")).content(example.getKeyword() +
                                                     ": " + example.getDescription());

        html.div(class_("scrollit"));
        html.table(class_("example_data"));

        List<Row> rows = example.getRows();

        for (int j = 0; j < rows.size(); j++)
        {

            if (j == 0)
                html.thead();
            if (j == 1)
                html.tbody();

            html.tr();
            Row row = rows.get(j);
            List<String> cells = row.getCells();

            for (String cell : cells)
            {
                if (j == 0)
                    html.th(scope("col")).content(cell);
                else
                    html.td().content(cell);
            }//cell

            html._tr();
            if (j == 0)
                html._thead();
        }//row

        html._tbody();
        html._table();
        html._div();
    }

    private void _addScenarioLinks(HtmlCanvas html) throws Throwable
    {

        html.div(class_("feature_title").class_("background-color-cukes")).content("Feature: " + _feature.getName());
        html.br();

        if (_feature.getDescription() != null && !_feature.getDescription().isEmpty())
            html.textarea(class_("feature-description-box").name("feature-description").id("description").cols("20").rows("1").disabled("disable"))
                    .content(_feature.getDescription().trim());

        html.br();
        html.br();
        html.div(class_("scenario_links").class_("background-color-cukes")).content("Scenarios");
        html.br();

        for (Scenario scenario : _scenarios)
        {

            html.div();
            if (scenario.getKeyword().equalsIgnoreCase("background"))
                html.a(class_("scenario" + scenario.getNumber()).href("")).content(scenario.getKeyword());
            else
                html.a(class_("scenario" + scenario.getNumber()).href("")).content(scenario.getName());
            html._div();
            html.br();
        }

        html.br();
    }

    private void _addLeftNavigationPane(HtmlCanvas html) throws Throwable
    {

        html.table().tr().td();
        html.div(class_("full-height"));
        html.ul();
        for (Feature feature : _features)
        {
            html.li().a(href("/projects/" + _projectId + "/" + feature.getId() + "/").class_("full-h")).span().content(feature.getName())._a()._li();
            html.br();
        }
        html._ul();
        html._div();
        html._td();
        html.td();
        html.div(id("main-low"));
        html.br();
    }


    private void _renderScenarios(HtmlCanvas html) throws Throwable
    {


        for (Scenario scenario : _scenarios)
        {

            _addTags(html, scenario.getTags());

            _addScenarioDiv(html, scenario);

            _addScenarioName(html, scenario);

            _addTopLink(html);

            _addSteps(scenario.getSteps(), html);

            List<Example> examples = scenario.getExamples();

            _addExamplesTable(html, examples);

            if (!scenario.getKeyword().equalsIgnoreCase("background"))
                _addCommentsAndApproveButtons(html, scenario);


            html._div();

            html.br();
            html.br();
            html.br();


        }


        html.br();
        html.br();
        html._div()
                ._td()
                ._tr()
                ._table();

        html.html();
    }

    private void _addCommentsAndApproveButtons(HtmlCanvas html, Scenario scenario) throws IOException
    {

        if (!(scenario.getApproved()))
        {
            if (scenario.getComments().size() > 0)
            {
                html.span(id("comment-shows-here" + scenario.getNumber()))._span();
                for (String comment : scenario.getComments())
                {
                    html.br();

                    html.div(class_("comment-box-div")).p(class_("username-comment"))._p().
                            textarea(class_("display_p")
                                             .id("display-comment-p" + scenario.getNumber())
                                             .disabled("disabled"))
                            .content(comment)._div();

                }
            }
        }

        html.div(class_("comment-wrapper"));
        html.input(type("hidden").id("project-id").value(_projectId));
        html.input(type("hidden").id("feature-id").value(_featureId));

        if ((scenario.getApproved()))
        {

            html.input(type("button").class_("cukes-button-approved").id("approve").content(Integer.toString(scenario.getNumber()))
                               .value("Approved").disabled("Approved"))
                    .input(type("hidden").id("projectId").value(_projectId))
                    .input(type("hidden").id("featureId").value(_feature.getId()));

        }
        else
        {


            html.p(id("comment-shows-here" + scenario.getNumber()))._p();

            html.span(id("display-comment-box" + scenario.getNumber()))._span();

            html.textarea(class_("comments-box").name("comments").id("comments" + scenario.getNumber()).cols("20").rows("3").style("display:none;"))
                    .content("").br()
            ;

            html.span(id("add-comment-box" + scenario.getNumber()))._span();

            html.input(type("button").class_("cukes-button").id("add-comment" + scenario.getNumber()).content(Integer.toString(scenario.getNumber())).value("Comment"))
                    .input(type("hidden").id("projectId").value(_projectId))
                    .input(type("hidden").id("featureId").value(_feature.getId()));

            int totalScenarios = scenario.getTotalScenariosFromExampleTable();

            if (totalScenarios > 1)

                html.input(type("button").class_("cukes-button").id("approve").content(Integer.toString(scenario.getNumber()))
                                   .value("Approve " + totalScenarios + " scenarios"))
                        .input(type("hidden").id("projectId").value(_projectId))
                        .input(type("hidden").id("featureId").value(_feature.getId()));
            else

                html.input(type("button").class_("cukes-button").id("approve").content(Integer.toString(scenario.getNumber()))
                                   .value("Approve scenario"))
                        .input(type("hidden").id("projectId").value(_projectId))
                        .input(type("hidden").id("featureId").value(_feature.getId()));

        }


        html._div();
    }

    private void _addExamplesTable(HtmlCanvas html, List<Example> examples) throws Throwable
    {

        for (Example example : examples)
        {

            _addTable(example, html);

        }
    }

    private void _addTopLink(HtmlCanvas html) throws IOException
    {
        html.a(class_("top").href("")).content("Top");
    }

    private void _addScenarioName(HtmlCanvas html, Scenario scenario) throws IOException
    {
        if (scenario.getKeyword().equalsIgnoreCase("background"))
            html.span(class_("scenario_description")).content(scenario.getKeyword());
        else

            html.span(class_("scenario_description")).content(
                    scenario.getName());
    }

    private void _addFeatureTitleHeader(HtmlCanvas html) throws IOException
    {


    }

    @Override
    public void renderOn(HtmlCanvas html)
    {

        try
        {

            _project = _projectService.getProjectById(_projectId);
            _features = _featureService.fetchFeatures(_project);
            _feature = _featureService.getFeatureId(_projectId, _featureId).get();
            _scenarios = _scenarioService.fetchScenarios(_project, _featureId);

            addScriptsAndStyleSheets(html);

            renderScenarioHeader(html, _project.getName());

            _addFeatureTitleHeader(html);

            _addLeftNavigationPane(html);

            _addScenarioLinks(html);

            _addTags(html, _feature.getTags());

            _renderScenarios(html);

        }
        catch (FeatureNotFoundException e)
        {
            throw new RuntimeException("Feature not found. Replace this with rendering error page: ", e);

        }
        catch (ProjectNotFoundException e)
        {
            throw new RuntimeException("Project not found. Replace this with rendering error page: ", e);

        }
        catch (Throwable e)
        {
            throw new RuntimeException("IO Error. Replace this with rendering error page: ", e);
        }


    }
}