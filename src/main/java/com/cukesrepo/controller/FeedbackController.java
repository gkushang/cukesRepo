package com.cukesrepo.controller;


import java.io.IOException;

import com.cukesrepo.page.FeedbackPage;
import org.rendersnake.HtmlCanvas;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FeedbackController
{

    public FeedbackController
            (

            )
    {

    }

    @RequestMapping(value = {"/feedback/"})
    @ResponseBody
    public void renderProjectsPage
            (
                    HtmlCanvas html
            ) throws IOException
    {
        html.render(new FeedbackPage());
    }


}
