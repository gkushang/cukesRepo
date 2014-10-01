package com.cukesrepo.service.feature;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.FeatureNotFoundException;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;
import com.google.common.base.Optional;

import java.util.List;


public interface FeatureService {

    public List<Feature> fetchFeatures(Project project) throws FeatureNotFoundException, ProjectNotFoundException, ScenariosNotFoundException;

    public Optional<Feature> getFeatureId(String projectId, String featureId) throws FeatureNotFoundException;

    public void setEmailSent(String projectId, String featureId) throws FeatureNotFoundException, ProjectNotFoundException;

    public void deleteFeatures(String projectId);
}
