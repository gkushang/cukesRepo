package com.cukesrepo.service.email;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.EmailException;

public interface EmailService {

    public String sendReviewRequest(Project project, Feature feature) throws EmailException;
}




