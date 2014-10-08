package com.cukesrepo.component;


import com.cukesrepo.domain.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScenarioComponent {

    private static final Logger LOG = LoggerFactory.getLogger(ScenarioComponent.class);

    public List<Scenario> updateScenarios(List<Scenario> approvedScenarios, List<Scenario> gitScenarios) {

        for (Scenario approvedScenario : approvedScenarios) {
            for (Scenario gitScenario : gitScenarios) {
                if (approvedScenario.compareTo(gitScenario)) {

                    gitScenario.setApproved(approvedScenario.getApproved());

                    gitScenario.setReviewComments(approvedScenario.getReviewComments());
                }
            }
        }

        return gitScenarios;
    }

    public int getTotalScenariosPerFeature(List<Scenario> scenarios) {

        int totalScenarios = 0;

        LOG.info("Scenarios size '{}'", scenarios.size());

        if (scenarios.size() == 0)
            return totalScenarios;

        for (Scenario scenario : scenarios) {
            totalScenarios += scenario.getTotalScenariosFromExampleTable();
            LOG.info("total scenarios '{}'", totalScenarios);
        }

        return totalScenarios;

    }

    public float getTotalPercentageOfApprovedScenarios(int totalApprovedScenarios, int totalScenariosPerFeature) {

        if (totalScenariosPerFeature == 0)
            return totalScenariosPerFeature;


        return ((float) totalApprovedScenarios
                /
                (totalScenariosPerFeature) * 100);
    }

}
