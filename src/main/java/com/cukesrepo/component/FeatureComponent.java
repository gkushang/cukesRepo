package com.cukesrepo.component;


import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.FeatureStatus;
import org.springframework.stereotype.Component;

@Component
public class FeatureComponent {

    public void updateFeatureAttributes(Feature gitFeature, Feature dbFeature, float totalPercentageApprovedScenarios) {


        if (totalPercentageApprovedScenarios >= 100) {

            gitFeature.setStatus(FeatureStatus.APPROVED.get());

        } else if (dbFeature.getEmailSent()) {

            gitFeature.setStatus(FeatureStatus.UNDER_REVIEW.get());
            gitFeature.setEmailSent(true);

        } else
            gitFeature.setStatus(totalPercentageApprovedScenarios <= 0 ? FeatureStatus.NEED_REVIEW.get() : FeatureStatus.UNDER_REVIEW.get());

    }
}
