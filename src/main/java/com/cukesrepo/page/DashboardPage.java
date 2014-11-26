package com.cukesrepo.page;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.id;


public class DashboardPage extends HeaderFooter implements Renderable
{


    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {

        addScriptsAndStyleSheets(html);
        renderHeader(html, "dashboardPage");

        html
                .div(id("chartContainer1").style("height: 400px;width: 400px;display: inline-block;position:absolute;left:320px").class_("shadow"))
                .content("null")
                .div(id("chartContainer2").style("height: 400px;width: 400px;display: inline-block;position:relative;left:720px").class_("shadow"))
                .content("null")
                .br()
                .div(id("chartContainer3").style("height: 400px;width: 400px;display: inline-block;position:absolute;left:320px").class_("shadow"))
                .content("null")
                .div(id("chartContainer4").style("height: 400px;width: 400px;display: inline-block;position:relative;left:720px").class_("shadow"))
                .content("null")
        ;
    }
}
