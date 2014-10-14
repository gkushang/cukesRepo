package com.cukesrepo.component;


import com.cukesrepo.domain.Email;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class EmailComponent
{

    private static final Logger LOG = LoggerFactory.getLogger(EmailComponent.class);

    public Email getReviewEmailTemplateFor(Project project, Feature feature)
    {

        Email email = new Email();

        email.setSubject("[" + project.getName() + "]" + " Review request for " + feature.getName() + " feature");

        String body = "<h4>You can review, comment on or approve this feature file at</h4>\n\n"
                + _getFeatureFileUrl(project, feature);

        email.setBody(body);
        email.setTo(project.getEmailPo());

        LOG.info("getReviewEmailTemplateFor Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;
    }

    private String _getFeatureFileUrl(Project project, Feature feature)
    {
        String host = Utils.getHostName();
        String port = System.getProperty("jetty.port");

        return "http://" + host + ":" + port + "/projects/" + project.getId() + "/" + feature.getId() + "/";
    }
}
