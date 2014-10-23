package com.cukesrepo.component;


import com.cukesrepo.domain.Email;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.utils.Utils;
import org.apache.commons.lang3.StringUtils;
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

        email.setSubject("" + project.getName() + ":" + " Review request for " + feature.getName() + " feature");

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "Can you review, comment or approve scenario(s) for <b><a href=\"" +
                _getFeatureFileUrl(project, feature) + "\">" +
                feature.getName() + "</a></b> feature?<br><br>";
        body += "You can view acceptance-scenarios at:<br><br>&#x09;" + _getFeatureFileUrl(project, feature) + "<br><br>";

        body += "<br>_<br><a href=\"http://go/cukes\" style=font-size:9pt>go/cukes</a>";

        body += "</BODY>";

        email.setBody(body);
        email.setTo(project.getEmailPo());

        LOG.info("getReviewEmailTemplateFor Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;
    }

    private String _getFeatureFileUrl(Project project, Feature feature)
    {
        String host = Utils.getHostName();
        String port = System.getProperty("jetty.port");

        if (StringUtils.isNotBlank(port))
            return "http://" + host + ":" + port + "/projects/" + project.getId() + "/" + feature.getId() + "/";
        else
            return "http://" + host + ".lvs01.dev.ebayc3.com/projects/" + project.getId() + "/" + feature.getId() + "/";
    }
}
