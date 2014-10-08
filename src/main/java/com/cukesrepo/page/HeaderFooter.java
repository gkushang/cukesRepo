package com.cukesrepo.page;


import java.io.IOException;

import com.cukesrepo.domain.Project;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.href;


public class HeaderFooter
{


    protected void addScriptsAndStyleSheets(HtmlCanvas html) throws IOException
    {

        html.head()
                .macros().javascript("/../../resources/scripts/jquery-1.10.2.min.js")
                .macros().javascript("/../../resources/scripts/email.js")
                .macros().javascript("/../../resources/scripts/canvasjs.min.js")
                .macros().javascript("/../../resources/scripts/firstword.js")
                .macros().javascript("/../../resources/scripts/dashboard.js")
                .macros().javascript("/../../resources/scripts/approve.js")
                .macros().javascript("/../../resources/scripts/comment.js")
                .macros().javascript("/../../resources/scripts/scrollTo.js")
                .macros().javascript("/../../resources/scripts/addproject.js")
                .macros().javascript("/../../resources/scripts/login.js")
                .macros().javascript("/../../resources/scripts/updateproject.js")
                .macros().javascript("/../../resources/scripts/signup.js")
                .macros().stylesheet("/../../resources/css/dashboard.css")
                .macros().stylesheet("/../../resources/css/headerfooter.css")
                .macros().stylesheet("/../../resources/css/cukes.css")._head();
    }

    protected void renderHeader(HtmlCanvas html) throws IOException
    {

        html.html()
                .body(class_("background-color-cukes"))
                .div(class_("cukes-logo bgColorA"))
                .div(class_("pageTitle"))
                .span(class_("title"))
                .content(" ")
                .span(class_("titlePart"))
                .content(" ")
                .ul()
                .li()
                .a(href("/projects/").class_("full"))
                .content("Home")
                ._li()
                ._ul()
                ._div()
                ._div()
                .div()._div();
        ;

        html._body()
                .html();


    }

    protected void renderScenarioHeader(HtmlCanvas html, Project project) throws IOException
    {

        html.html()
                .body(class_("background-color-cukes"))
                .div(class_("cukes-logo bgColorA"))
                .div(class_("pageTitle"))
                .span(class_("title"))
                .content(" ")
                .span(class_("titlePart"))
                .content(" ")
                .ul()

                .li()
                .a(href("/projects/").class_("full"))
                .content("Home")
                ._li()

                .li()
                .a(href("/projects/" + project.getId() + "/").class_("full"))
                .content(project.getName())
                ._li()

                ._ul()
                ._div()
                ._div()
                .div()._div();
        ;

        html._body()
                .html();


    }

    protected void renderTitle(HtmlCanvas html) throws IOException
    {

        html.html()
                .body()
                .div(class_("cukes-logo bgColorA"))
                .div(class_("pageTitle"))
                .span(class_("title"))
                .content("cukes ")
                .span(class_("titlePart"))
                .content("Repo")
                ._div()
                ._div();

        html._body()
                .html();


    }


}
