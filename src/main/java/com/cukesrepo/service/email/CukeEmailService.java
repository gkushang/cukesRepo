package com.cukesrepo.service.email;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.EmailException;


public interface CukeEmailService
{

    String sendReviewRequest(Project project, Feature feature) throws EmailException;

    String sendReviewComment(Project project, Feature feature, String scenarioName, String comment) throws EmailException;

    String sendApproved(Project projectById, Feature feature, String scenario_name);
}




