package com.cukesrepo.repository.feature;

import java.util.List;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.FeatureNotFoundException;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;
import com.google.common.base.Optional;


public interface FeatureRepository
{

    public List<Feature> fetchFeatures(Project project) throws FeatureNotFoundException, ProjectNotFoundException, ScenariosNotFoundException;

    public Optional<Feature> getFeatureById(String projectId, String id) throws FeatureNotFoundException;

    public void setEmailSentAndStatus(String projectId, String featureId) throws FeatureNotFoundException, ProjectNotFoundException;

    void deleteFeatures(String projectId);
}
