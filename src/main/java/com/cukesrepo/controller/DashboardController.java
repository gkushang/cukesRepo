package com.cukesrepo.controller;

import com.cukesrepo.page.DashboardPage;
import com.cukesrepo.service.project.ProjectService;
import org.rendersnake.HtmlCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: klohani
 * Date: 12/12/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DashboardController {

    private final ProjectService _projectService;

    @Autowired
    public DashboardController(ProjectService _projectService) {
        this._projectService = _projectService;
    }

    @RequestMapping(value = {"/dashboard/charts/"})
    @ResponseBody
    public void DashboardPage(HtmlCanvas html) throws IOException {
        html.render(new DashboardPage());
    }
}
