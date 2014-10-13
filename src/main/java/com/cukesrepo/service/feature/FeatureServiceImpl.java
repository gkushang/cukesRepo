package com.cukesrepo.service.feature;

import java.util.List;

import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.FeatureNotFoundException;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;
import com.cukesrepo.repository.feature.FeatureRepository;
import com.google.common.base.Optional;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FeatureServiceImpl implements FeatureService
{

    private final FeatureRepository _featureRepository;

    private static final Logger LOG = LoggerFactory.getLogger(FeatureService.class);

    @Autowired
    public FeatureServiceImpl
            (
                    FeatureRepository featureRepository
            )
    {

        Validate.notNull(featureRepository, "featureRepository cannot be null");

        _featureRepository = featureRepository;
    }

    public List<Feature> fetchFeatures(Project project) throws FeatureNotFoundException, ProjectNotFoundException, ScenariosNotFoundException
    {

        Validate.notNull(project, "project cannot be null");

        return _featureRepository.fetchFeatures(project);

    }

    public Optional<Feature> getFeatureId(String projectId, String featureId) throws FeatureNotFoundException
    {

        Validate.notEmpty(featureId, "featureId cannot be empty or null");

        Optional<Feature> feature = _featureRepository.getFeatureById(projectId, featureId);

        if (feature.isPresent())
        {

            LOG.error("Feature found by id '{}'", featureId);
            return feature;

        }
        else
        {

            LOG.error("Feature not found by id '{}'", featureId);
            throw new FeatureNotFoundException("Feature '" + featureId + "' not found");
        }
    }

    @Override
    public void setEmailSent(String projectId, String featureId) throws FeatureNotFoundException, ProjectNotFoundException
    {

        LOG.info("Set feature file emailSent flag");

        _featureRepository.setEmailSentAndStatus(projectId, featureId);

    }

    @Override
    public void deleteFeatures(String projectId)
    {

        LOG.info("Delete all the featues for project '{}'", projectId);

        _featureRepository.deleteFeatures(projectId);
    }


}
