package com.cukesrepo.component;


import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.FeatureStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class FeatureComponent
{

    public void updateFeatureAttributes(Feature gitFeature, Feature dbFeature, int totalApprovedScenarios,
                                        int totalScenarios)
    {


        if ((totalScenarios - totalApprovedScenarios) == 0)
        {

            gitFeature.setStatus(FeatureStatus.APPROVED.get());

        }
        else if (dbFeature.getEmailSent())
        {

            gitFeature.setStatus(FeatureStatus.UNDER_REVIEW.get());
            gitFeature.setEmailSent(true);

        }
        else
        {
            gitFeature.setStatus((totalScenarios - totalApprovedScenarios) < totalScenarios
                                 ? FeatureStatus.UNDER_REVIEW.get()
                                 : FeatureStatus.NEED_REVIEW.get());
        }

        if (StringUtils.isNotBlank(dbFeature.getDiscussion()))
        {
            gitFeature.setDiscussion(dbFeature.getDiscussion());
        }

    }
}
