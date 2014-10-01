package com.cukesrepo.component;


import com.cukesrepo.domain.Email;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailComponent {

    private static final Logger LOG = LoggerFactory.getLogger(EmailComponent.class);

    private final String TO_EMAIL_ADDRESS = "kugajjar@paypal.com";


    public Email getReviewEmailTemplateFor(Project project, Feature feature) {

        Email email = new Email();

        email.setSubject("[" + project.getName() + "]" + " Review request for " + feature.getName() + " feature");

        String body = "<h4>You can review, comment on or approve this feature file at</h4>\n\n"
                + "http://localhost:8800/projects/" + project.getId() + "/" + feature.getId() + "/";

        email.setBody(body);
        email.setTo(TO_EMAIL_ADDRESS);

        LOG.info("getReviewEmailTemplateFor Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;
    }
}
