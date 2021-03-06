package com.cukesrepo.controller;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cukesrepo.exceptions.EmailException;
import com.cukesrepo.exceptions.FeatureNotFoundException;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.service.email.CukeEmailService;
import com.cukesrepo.service.feature.FeatureService;
import com.cukesrepo.service.project.ProjectService;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmailController
{

    private final FeatureService _featureService;
    private final ProjectService _projectService;
    private final CukeEmailService _emailService;


    @Autowired
    public EmailController
            (
                    FeatureService featureService,
                    ProjectService projectService,
                    CukeEmailService emailService
            )
    {

        Validate.notNull(featureService, "featureService cannot be null");
        Validate.notNull(projectService, "projectService cannot be null");
        Validate.notNull(emailService, "emailService cannot be null");

        _featureService = featureService;
        _projectService = projectService;
        _emailService = emailService;

    }

    @RequestMapping(value = {"/{featureId}/{projectId}/email/review-request"}, method = RequestMethod.GET)
    @ResponseBody
    public void sendReviewRequest(@PathVariable String featureId, @PathVariable String projectId) throws IOException
    {


        try
        {
            _emailService.sendReviewRequest
                    (
                            _projectService.getProjectById(projectId),
                            _featureService.getFeatureId(projectId, featureId).get()
                    );

            _featureService.setEmailSent(projectId, featureId);

        }
        catch (FeatureNotFoundException e)
        {
            throw new RuntimeException("Feture not found. Replace this with rendering error page: ", e);

        }
        catch (ProjectNotFoundException e)
        {
            throw new RuntimeException("Project not found. Replace this with rendering error page: ", e);

        }
        catch (EmailException e)
        {
            throw new RuntimeException("Email sent failed. Replace this with rendering error page: ", e);

        }
    }

    @RequestMapping(value = {"/{projectId}/{featureId}/{scenario_name}/send-email-comment"}, method = RequestMethod.POST)
    @ResponseBody
    public void sendReviewComment
            (
                    HttpServletRequest request,
                    @PathVariable String projectId,
                    @PathVariable String featureId,
                    @PathVariable String scenario_name
            ) throws IOException

    {
        String comments = request.getParameter("comments");

        if (StringUtils.isNotEmpty(comments))
        {
            try
            {
                _emailService.sendReviewComment(_projectService.getProjectById(projectId),
                                                _featureService.getFeatureId(projectId, featureId).get(),
                                                scenario_name,
                                                comments);
            }
            catch (ProjectNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (FeatureNotFoundException e)
            {
                e.printStackTrace();
            }

        }
    }


    @RequestMapping(value = {"/{projectId}/{featureId}/{scenario_name}/send-email-approved"}, method = RequestMethod.POST)
    @ResponseBody
    public void sendApproved
            (
                    HttpServletRequest request,
                    @PathVariable String projectId,
                    @PathVariable String featureId,
                    @PathVariable String scenario_name
            ) throws IOException

    {

        try
        {
            _emailService.sendApproved(_projectService.getProjectById(projectId),
                                       _featureService.getFeatureId(projectId, featureId).get(),
                                       scenario_name);
        }
        catch (ProjectNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (FeatureNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = {"/send-feedback"}, method = RequestMethod.POST)
    @ResponseBody
    public void sendReviewFeedback
            (
                    HttpServletRequest request
            ) throws IOException

    {
        String comments = request.getParameter("comments");
        String feedbackType = request.getParameter("radioVal");

        if (StringUtils.isNotEmpty(comments))
        {
            _emailService.sendFeedback(comments, feedbackType);
        }
    }


    @RequestMapping(value = {"/user/request-project/email"})
    @ResponseBody
    protected void emailRequestProject(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        try
        {
            _emailService.sendAddProjectRequest(request.getParameterMap());
        }
        catch (IllegalArgumentException e)
        {
            response.reset();
            response.setContentType("text/plain");
            response.getWriter().write(e.getMessage());
        }
        catch (EmailException e)
        {
            response.reset();
            response.setContentType("text/plain");
            response.getWriter().write("Email address is invalid");
        }

    }

    @RequestMapping(value = {"/user/add-project/persist"})
    @ResponseBody
    protected void persistProject(HttpServletRequest request) throws IOException
    {
        _projectService.addProject(request.getParameterMap());
        _emailService.sendProjectAddedEmail(request.getParameterMap());

    }


}
