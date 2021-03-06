package com.cukesrepo.service.email;

import java.util.Map;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.EmailException;


public interface CukeEmailService
{

    String sendReviewRequest(Project project, Feature feature) throws EmailException;

    String sendReviewComment(Project project, Feature feature, String scenarioName, String comment) throws EmailException;

    String sendApproved(Project projectById, Feature feature, String scenario_name);

    String sendFeedback(String comments, String feedbackType);

    void sendAddProjectRequest(Map<String, String[]> parameterMap);

    void sendProjectAddedEmail(Map<String, String[]> parameterMap);
}




