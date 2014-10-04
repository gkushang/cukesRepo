package com.cukesrepo.page;

import java.io.IOException;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.FeatureStatus;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.FeatureNotFoundException;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;
import com.cukesrepo.service.feature.FeatureService;
import com.cukesrepo.service.scenario.ScenarioService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.*;


public class FeaturesPage extends HeaderFooter implements Renderable
{

    private final Project _project;
    private final FeatureService _featureService;
    private final String EMAIL_ELEMENT = "email-request";
    private final ScenarioService _scenarioService;


    public FeaturesPage
            (
                    FeatureService featureService,
                    ScenarioService scenarioService,
                    Project project
            )
    {

        Validate.notNull(featureService, "featureService cannot be null");
        Validate.notNull(project, "project cannot be null");
        Validate.notNull(scenarioService, "scenarioService cannot be null");

        _featureService = featureService;
        _project = project;
        _scenarioService = scenarioService;

    }


    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {

        addScriptsAndStyleSheets(html);
        renderHeader(html);

        int cumulativeScenarios = 0;

        int alternate = 0;

        html.html()
                .body(class_("background-color-cukes"));


        html.h2().span(id("project-title")).content(_project.getName().toLowerCase())._h2();

//        html.div(id("feature-page"));
        html.div(class_("CSSTableGenerator"));
        html.table()
                .tr()
                .th().content("Features")
                .th().content("Total Scenarios")
                .th().content("Approved")
//                .th().content("Status")
                .th().content("Review Request (PO)")
                ._tr();

        try
        {


            for (Feature feature : _featureService.fetchFeatures(_project))
            {

                int totalScenarios = feature.getTotalScenarios();

                cumulativeScenarios = cumulativeScenarios + totalScenarios;

                html.input(type("hidden").id("project-id").value(_project.getId()));


                if (alternate % 2 == 0)
                    html.tr(class_("alt"));
                else
                    html.tr();

                html.td().a(class_("no_decoration").href(feature.getId() + "/")).span().content(feature.getName())._a()._td()

                        .td().span().content(Integer.toString(totalScenarios))._td()
                        .td().span().content(Integer.toString(_scenarioService.getTotalPercentageApprovedScenarios(_project.getId(), feature.getId())))._td()
                ;//e.td().span(id("status-" + feature.getId())).content(feature.getStatus())._td();


                if (feature.getStatus().equalsIgnoreCase(FeatureStatus.APPROVED.get()))
                    html.td()
                            ._td();

                else if (feature.getStatus().equalsIgnoreCase(FeatureStatus.UNDER_REVIEW.get()))
                    html.td()
                            .input(type("button").class_("cukes-button").id(EMAIL_ELEMENT).value("resend for review").content(feature.getId()))
                            ._td();

                else if (feature.getStatus().equalsIgnoreCase(FeatureStatus.NEED_REVIEW.get()))
                    html.td()
                            .input(type("button").class_("cukes-button").id(EMAIL_ELEMENT).value("send for review").content(feature.getId()))
                            ._td();

                html._tr();

                alternate++;


            }
        }
        catch (FeatureNotFoundException e)
        {
            throw new RuntimeException("Feature not found. Replace this with rendering error page: ", e);

        }
        catch (ProjectNotFoundException e)
        {
            throw new RuntimeException("Project not found. Replace this with rendering error page: ", e);

        }
        catch (ScenariosNotFoundException e)
        {
            throw new RuntimeException("Scenario not found. Replace this with rendering error page: ", e);
        }

//        html.tfoot().tr().td().content("Total No of scenarios").td().content(Integer.toString(cumulativeScenarios)).td().content("").td().content("").td().content("").td().content("")._tr()._tfoot();
        html.tfoot().tr().td().content("Total No of scenarios").td().content(Integer.toString(cumulativeScenarios)).td().content("").td().content("")._tr()._tfoot();
        html._table();

        html._div();
        html.br();
        html.br();
        html.br();
        html.br();
        html._body();

    }
}
