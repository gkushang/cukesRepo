package com.cukesrepo.repository.feature;

import java.util.ArrayList;
import java.util.List;

import com.cukesrepo.component.FeatureComponent;
import com.cukesrepo.component.GitComponent;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.FeatureStatus;
import com.cukesrepo.domain.Project;
import com.cukesrepo.domain.Scenario;
import com.cukesrepo.exceptions.FeatureNotFoundException;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;
import com.cukesrepo.repository.scenario.ScenarioRepository;
import com.google.common.base.Optional;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class FeatureRepositoryImpl implements FeatureRepository
{

    private final MongoTemplate _mongoTemplate;
    private final GitComponent _gitComponent;
    private final ScenarioRepository _scenarioRepository;

    private static final Logger LOG = LoggerFactory.getLogger(FeatureRepositoryImpl.class);
    private final FeatureComponent _featureComponent;

    @Autowired
    public FeatureRepositoryImpl
            (
                    GitComponent gitComponent,
                    ScenarioRepository scenarioRepository,
                    FeatureComponent featureComponent,
                    MongoTemplate mongoTemplate
            )
    {

        Validate.notNull(gitComponent, "gitComponent cannot be null");
        Validate.notNull(scenarioRepository, "scenarioRepository cannot be null");
        Validate.notNull(featureComponent, "featureComponent cannot be null");
        Validate.notNull(mongoTemplate, "mongoTemplate cannot be null");

        _gitComponent = gitComponent;
        _scenarioRepository = scenarioRepository;
        _mongoTemplate = mongoTemplate;
        _featureComponent = featureComponent;

    }

    public List<Feature> fetchFeatures(Project project) throws FeatureNotFoundException, ProjectNotFoundException, ScenariosNotFoundException
    {

        Validate.notNull(project, "project cannot be null");

        List<Feature> gitFeatures = _gitComponent.fetchFeatures(project);

        LOG.info("Fetch '{}' features for Project '{}'", gitFeatures.size(), project.getId());

        for (Feature gitFeature : gitFeatures)
        {

            if (getFeatureById(project.getId(), gitFeature.getId()).isPresent())
            {
                _featureComponent.updateFeatureAttributes
                        (
                                gitFeature,
                                getFeatureById(project.getId(), gitFeature.getId()).get(),
                                _scenarioRepository.getTotalApprovedScenarios(project.getId(), gitFeature.getId()),
                                gitFeature.getTotalScenarios()
                        );

                if (StringUtils.isNotBlank(getFeatureById(project.getId(), gitFeature.getId()).get().getDiscussion()))
                {
                    gitFeature.setDiscussion(getFeatureById(project.getId(), gitFeature.getId()).get().getDiscussion());
                }
            }

        }

        _mongoTemplate.remove
                (
                        new

                                Query(Criteria.where(Feature.PROJECTID)

                                              .

                                                      is(project.getId()

                                                      )),
                        Feature.class
                );

        _mongoTemplate.remove
                (
                        new

                                Query(Criteria.where(Feature.PROJECTID)

                                              .

                                                      is(project.getId()

                                                      )),
                        Scenario.class
                );

        LOG.info("Insert '{}' features to DB for Project '{}'", gitFeatures.size(), project.getId());

        List<Feature> gitFeatures_1 = new ArrayList<Feature>();
        List<Feature> gitFeatures_2 = new ArrayList<Feature>();

        for (int i = 0; i < 20; i++)
        {
            gitFeatures_1.add(gitFeatures.get(i));
        }

        for (int i = 21; i < 40; i++)
        {
            gitFeatures_2.add(gitFeatures.get(i));

        }

        _mongoTemplate.insertAll(gitFeatures);

//        for (Feature feature : gitFeatures_1)
//        {
//            _mongoTemplate.insert(feature);
//        }
//        _mongoTemplate.insertAll(gitFeatures_1);

//        _mongoTemplate.insertAll(gitFeatures);


        return gitFeatures;

    }


    public Optional<Feature> getFeatureById(String projectId, String featureId) throws FeatureNotFoundException
    {

        Query query = new Query((Criteria.where(Feature.ID).is(featureId)).and(Feature.PROJECTID).is(projectId));

        LOG.info("Get feature name for featureId '{}' and Project '{}'", featureId, projectId);

        LOG.info("Get feature name by query '{}'", query);

        Optional<Feature> featureOptional = Optional.fromNullable(_mongoTemplate.findOne(query, Feature.class));

        return featureOptional;

    }

    @Override
    public void setEmailSentAndStatus(String projectId, String featureId) throws FeatureNotFoundException, ProjectNotFoundException
    {

        Optional<Feature> featureOptional = getFeatureById(projectId, featureId);

        if (featureOptional.isPresent())
        {

            Feature feature = featureOptional.get();
            feature.setStatus(FeatureStatus.UNDER_REVIEW.get());
            feature.setEmailSent(true);
            _mongoTemplate.remove(feature);
            _mongoTemplate.insert(feature);

            LOG.info("Email status set to true for feature '{}'", featureId);
        }
        else
            throw new FeatureNotFoundException("Feature " + featureId + " was not found");

    }

    @Override
    public void deleteFeatures(String projectId)
    {
        _mongoTemplate.findAndRemove(new Query(Criteria.where(Feature.PROJECTID).is(projectId)), Feature.class);
    }

    @Override
    public void addDiscussion(String projectId, String featureId, String discussions) throws FeatureNotFoundException
    {
        Optional<Feature> featureOptional = getFeatureById(projectId, featureId);

        if (featureOptional.isPresent())
        {
            Feature feature = featureOptional.get();
            feature.setDiscussion(discussions);
            _mongoTemplate.remove(feature);
            _mongoTemplate.insert(feature);

            LOG.info("Discussion '{}' is updated for feature '{}'", discussions, featureId);
        }
        else
            throw new FeatureNotFoundException("Feature " + featureId + " was not found");
    }

    private Query _queryToFindFeatureById(String projectId, String featureId)
    {

        return new Query
                (
                        Criteria.
                                where(Scenario.PROJECTID).
                                is(projectId).

                                and(Scenario.FEATUREID).
                                is(featureId)
                );
    }
}