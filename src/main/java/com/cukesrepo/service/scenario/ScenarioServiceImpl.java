package com.cukesrepo.service.scenario;

import java.util.List;

import com.cukesrepo.domain.Project;
import com.cukesrepo.domain.Scenario;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;
import com.cukesrepo.repository.scenario.ScenarioRepository;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScenarioServiceImpl implements ScenarioService
{

    private final ScenarioRepository _scenarioRepository;

    @Autowired
    public ScenarioServiceImpl
            (
                    ScenarioRepository scenarioRepository
            )
    {

        Validate.notNull(scenarioRepository, "scenarioRepository cannot be null");

        _scenarioRepository = scenarioRepository;
    }

    public List<Scenario> fetchScenarios(Project project, String featureId) throws ScenariosNotFoundException, ProjectNotFoundException
    {

        return _scenarioRepository.fetchScenarios(project, featureId);
    }

    public void approveScenario(String projectId, String featureId, String scenarioNumber) throws ScenariosNotFoundException
    {

        _scenarioRepository.approveScenario(projectId, featureId, scenarioNumber);

    }

    public void addComment(String projectId, String featureId, String scenarioNumber, String comment) throws ScenariosNotFoundException
    {

        _scenarioRepository.addComment(projectId, featureId, scenarioNumber, comment);
    }

    @Override
    public Integer getTotalPercentageApprovedScenarios(String projectId, String featureId)
    {
        return _scenarioRepository.getTotalPercentageApprovedScenarios(projectId, featureId);
    }

    @Override
    public void deleteScenarios(String projectId)
    {
        _scenarioRepository.deleteScenarios(projectId);
    }
}
