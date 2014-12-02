package com.cukesrepo.page;


import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.*;


public class FeedbackPage extends HeaderFooter implements Renderable
{


    public FeedbackPage()

    {


    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {

        addScriptsAndStyleSheets(html);

        renderHeader(html, "feedbackPage");

        html.html()
                .body();

        html.br();
        html.br();
        html.div(class_("feedback-outer"));

        html.div(class_("feedback-inner"));
        html.div(class_("feedback-title-div feedback-margin"))
                .span(id("feedback-add-title")).content("Your Feedback About Cukes Repo").br()
                ._div();

        html.div(class_("feedback-box-div feedback-margin"));
        html.span(id("update-elements-name")).content("Thanks for taking the time to give us feedback. Let us know how we can improve your experience with Cukes. Please provide any comments you have and send us your feedback.").br()


//                <input type="radio" checked="checked" name="title" value="Mr" id="mr" /> Mr.</label><br />
//        <label for="mrs"> <input type="radio" name="title" value="Mrs" id="mrs" /> Mrs.</label>br />


                .div(class_("some-class"))
                .input(type("radio").name("group1").value("Suggestion")).label(class_("feedback-elements-radio")).content("Suggestion").br()
                .input(type("radio").name("group1").value("Issue")).label(class_("feedback-elements-radio")).content("Issue").br()
                .input(type("radio").checked("checked").name("group1").value("General Comment")).label(class_("feedback-elements-radio")).content("General Comment")
                ._div()

                .div(class_("urfdbk")).span(id("update-elements-name").class_("feedback-elements")).content("Your feedback")._div().
                textarea(class_("feedback_comment-text-area"))
                .content("")

                ._div();


        html.div(class_("feedback-send feedback-margin")).span(id("feedback-error")).content("").input(type("button").class_("cukes-button").id("send-feedback").value("Send Feedback"))._div();

        html._div();
        html._div();

        html._body()
                ._html();

    }
}
