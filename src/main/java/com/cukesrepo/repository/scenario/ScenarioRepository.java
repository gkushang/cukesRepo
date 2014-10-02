package com.cukesrepo.repository.scenario;

import java.util.List;

import com.cukesrepo.domain.Project;
import com.cukesrepo.domain.Scenario;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;


public interface ScenarioRepository
{

    public List<Scenario> fetchScenarios(Project project, String featureId) throws ProjectNotFoundException, ScenariosNotFoundException;

    public List<Scenario> getApprovedScenariosFromDB(String projectId, String featureId);

    public void approveScenario(String projectId, String featureId, String scenarioNumber) throws ScenariosNotFoundException;

    public void addComment(String projectId, String featureId, String scenarioNumber, String comment);

    public Integer getTotalPercentageApprovedScenarios(String projectId, String featureId);

    public int getTotalScenariosPerFeature(String projectId, String featureId);

    public void insertScenarios(String projectId, String featureId, List<Scenario> gitScenarios);

    public void deleteScenarios(String projectId);
}
