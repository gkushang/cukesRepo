package com.cukesrepo.service.scenario;


import java.util.List;

import com.cukesrepo.domain.Project;
import com.cukesrepo.domain.Scenario;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.ScenariosNotFoundException;


public interface ScenarioService {

    public List<Scenario> fetchScenarios(Project project, String featureId) throws ScenariosNotFoundException, ProjectNotFoundException;

    public void approveScenario(String projectId, String featureId, String scenarioNumber) throws ScenariosNotFoundException;

    public void addComment(String projectId, String featureId, String scenarioNumber, String comment) throws ScenariosNotFoundException;

    public Integer getTotalPercentageApprovedScenarios(String projectId, String featureId);

    public void deleteScenarios(String projectId);
}
