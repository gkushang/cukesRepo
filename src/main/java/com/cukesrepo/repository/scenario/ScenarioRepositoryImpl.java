package com.cukesrepo.repository.scenario;

import java.util.List;

import com.cukesrepo.component.GitComponent;
import com.cukesrepo.component.ScenarioComponent;
import com.cukesrepo.domain.Project;
import com.cukesrepo.domain.Scenario;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;
import com.google.common.base.Optional;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class ScenarioRepositoryImpl implements ScenarioRepository
{

    private final MongoTemplate _mongoTemplate;
    private final GitComponent _gitComponent;
    private final ScenarioComponent _scenarioComponent;

    private static final Logger LOG = LoggerFactory.getLogger(ScenarioRepository.class);

    @Autowired
    public ScenarioRepositoryImpl
            (
                    GitComponent gitComponent,
                    ScenarioComponent scenarioComponent,
                    MongoTemplate mongoTemplate
            )
    {

        Validate.notNull(gitComponent, "gitComponent cannot be null");
        Validate.notNull(scenarioComponent, "scenarioComponent cannot be null");
        Validate.notNull(mongoTemplate, "mongoTemplate cannot be null");

        _gitComponent = gitComponent;
        _scenarioComponent = scenarioComponent;
        _mongoTemplate = mongoTemplate;
    }

    @Override
    public void insertScenarios(String projectId, String featureId, List<Scenario> gitScenarios)
    {

        List<Scenario> approvedScenarios = _getApprovedAndOrWithCommentsScenariosFromDB(projectId, featureId);

        _mongoTemplate.remove(_queryToFindAllScenarios(projectId, featureId), Scenario.class);

        _mongoTemplate.insertAll(_scenarioComponent.updateScenarios(approvedScenarios, gitScenarios));
    }

    @Override
    public void deleteScenarios(String projectId)
    {

        LOG.info("Delete all sceanrios for project '{}'", projectId);

        _mongoTemplate.findAndRemove(new Query(Criteria.where(Scenario.PROJECTID).is(projectId)), Scenario.class);
    }

    @Override
    public Integer getTotalApprovedScenarios(String projectId, String featureId)
    {

        int totalApprovedScenarios = 0;

        for (Scenario scenario : getApprovedScenariosFromDB(projectId, featureId))
            totalApprovedScenarios += scenario.getTotalScenariosFromExampleTable();

        LOG.info("Get total approved scenarios for project '{}' feature '{}' and they are '{}'",
                 projectId, featureId, totalApprovedScenarios);

        return totalApprovedScenarios;
//        return Math.round(
//                _scenarioComponent.getTotalPercentageOfApprovedScenarios
//                        (
//                                totalApprovedScenarios,
//                                getTotalScenariosPerFeature(projectId, featureId)
//                        ));
    }

    @Override
    public int getTotalScenariosPerFeature(String projectId, String featureId)
    {

        LOG.info("Total Scenarios per feature '{}'", featureId);

        return _scenarioComponent.getTotalScenariosPerFeature(_mongoTemplate.find(_queryToFindAllScenarios(projectId, featureId), Scenario.class));
    }


    public List<Scenario> getApprovedScenariosFromDB(String projectId, String featureId)
    {

        Query query;
        query = new Query
                (
                        Criteria.
                                where(Scenario.PROJECTID).is(projectId).
                                and(Scenario.FEATUREID).is(featureId).
                                and(Scenario.APPROVED).is(true)
                );

        return _mongoTemplate.find(query, Scenario.class);

    }

    public List<Scenario> fetchScenarios(Project project, String featureId) throws ProjectNotFoundException, ScenariosNotFoundException
    {

        LOG.info("Fetch scenarios for project '{}' feature '{}'", project.getName(), featureId);

        List<Scenario> gitScenarios = _gitComponent.fetchScenarios(project, featureId);

        LOG.info("fetchScenarios No. of Scenarios found from feature files are '{}' for project '{}' and featureId '{}'", gitScenarios.size(),
                 project.getId(), featureId);

        insertScenarios(project.getId(), featureId, gitScenarios);

        LOG.info("Scenario inserted !");

        return _mongoTemplate.find(_queryToFindAllScenarios(project.getId(), featureId), Scenario.class);

    }

    @Override
    public void approveScenario(String projectId, String featureId, String scenarioNumber) throws ScenariosNotFoundException
    {

        LOG.info("Approve Scenario '{}' for Project '{}' and Feature '{}'", scenarioNumber, projectId, featureId);

        Optional<Scenario> scenario = _findOneScenarioByNumber(projectId, featureId, scenarioNumber);

        if (scenario.isPresent())
        {

            scenario.get().setApproved(true);

            _mongoTemplate.save(scenario.get());

        }
        else
        {

            throw new ScenariosNotFoundException("Sorry, we couldn't find a scenario");
        }

        LOG.info("Scenario was approved for Project '{}' and Feature '{}'", scenarioNumber, projectId, featureId);
    }

    @Override
    public void addComment(String projectId, String featureId, String scenarioNumber, String comment)
    {

        LOG.info("Add comment '{}' to Scenario '{}' for Project '{}' and Feature '{}'", comment, scenarioNumber, projectId, featureId);

        Optional<Scenario> scenario = _findOneScenarioByNumber(projectId, featureId, scenarioNumber);

        scenario.get().getReviewComments().add(comment);

        _mongoTemplate.updateFirst
                (
                        _queryToFindOneScenarioByNumber
                                (
                                        projectId, featureId, scenarioNumber
                                ),

                        Update.update
                                (
                                        Scenario.REVIEWCOMMENTS, scenario.get().getReviewComments()
                                ),

                        Scenario.class
                );

        LOG.info("Comment '{}' added to scenario '{}'", comment, scenario.get().getName());

    }


    private Optional<Scenario> _findOneScenarioByNumber(String projectId, String featureId, String scenarioNumber)
    {

        Optional<Scenario> scenarioOptional = Optional.fromNullable(_mongoTemplate.findOne
                (
                        _queryToFindOneScenarioByNumber(projectId, featureId, scenarioNumber),
                        Scenario.class
                ));

        if (scenarioOptional.isPresent())

            LOG.info("Scenario by number '{}' found for Project '{}' and FeatureID '{}'", scenarioNumber, projectId, featureId);

        else

            LOG.warn("Scenario by number '{}' not found for Project '{}' and FeatureID '{}'", scenarioNumber, projectId, featureId);

        return scenarioOptional;

    }

    private Query _queryToFindOneScenarioByNumber(String projectId, String featureId, String scenarioNumber)
    {

        return new Query
                (
                        Criteria.
                                where(Scenario.PROJECTID).
                                is(projectId).

                                and(Scenario.FEATUREID).
                                is(featureId).

                                and(Scenario.NUMBER).
                                is(Integer.parseInt(scenarioNumber))
                );
    }

    private Query _queryToFindAllScenarios(String projectId, String featureId)
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

    private List<Scenario> _getApprovedAndOrWithCommentsScenariosFromDB(String projectId, String featureId)
    {

        return _mongoTemplate.find

                (
                        new Query
                                (
                                        Criteria.
                                                where(Scenario.PROJECTID).
                                                is(projectId).
                                                andOperator(Criteria.where(Scenario.FEATUREID).
                                                        is(featureId).
                                                        orOperator(Criteria.where(Scenario.APPROVED).
                                                                           is(true),
                                                                   Criteria.where(Scenario.REVIEWCOMMENTS).not().size(-1)))
                                ),

                        Scenario.class
                );

    }

}
