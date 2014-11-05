package com.cukesrepo.service.email;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.EmailException;


public interface CukeEmailService
{

    public String sendReviewRequest(Project project, Feature feature) throws EmailException;

    String sendReviewComment(Project project, Feature feature, String scenarioDescription, String comment) throws EmailException;
}




