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

        email.setSubject("Review request for " + feature.getName() + " feature: " + project.getName());

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "Can you review, comment or approve scenarios for <b><a href=\"" +
                _getFeatureFileUrl(project, feature) + "\">" +
                feature.getName() + "</a></b> feature?<br><br>";
        body += "You can view acceptance-scenarios at:<br><br>&#x09;" + _getFeatureFileUrl(project, feature) + "<br><br>";

        body = _getEmailFooter(body);

        body += "</BODY>";

        email.setBody(body);
        email.setTo(project.getEmailPo());
        email.setCc(project.getCollaborators());

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

    public Email getReviewCommentEmailTemplateFor(Project project, Feature feature, String scenarioName, String comment)
    {
        Email email = new Email();

        email.setSubject("Re: Review request for " + feature.getName() + " feature: " + project.getName());

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "<b>Review Comment</b> for <b><a href=\"" +
                _getFeatureFileUrl(project, feature) + "\">" +
                feature.getName() + "</a></b><br><br>";
        body += "<div style=color:#00008b;font-size:9.5pt;font-family:Calibri>>>Scenario: " + scenarioName + "</div><br>";
        body += "<div style=font-size:10.5pt;font-family:Calibri>\"" + comment + "\"</div><br><br>";

        body = _getEmailFooter(body);

        body += "</BODY>";

        email.setBody(body);
        email.setTo(project.getCollaborators());
        email.setCc(project.getEmailPo());

        LOG.info("getReviewComment TemplateFor Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;

    }

    private String _getEmailFooter(String body)
    {
        body += "_<br><a href=\"http://go/cukes\" style=font-size:9pt>go/cukes</a>";
        return body;
    }

    public Email getApprovedEmailTemplateFor(Project project, Feature feature, String scenarioName)
    {
        Email email = new Email();

        email.setSubject("Re: Review request for " + feature.getName() + " feature: " + project.getName());

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "<div style=color:#1a894b,padding=10px 10px><b>Approved!</b></div><br>";

        String linkToFeature = "<b><a href=\"" +
                _getFeatureFileUrl(project, feature) + "\">" +
                feature.getName() + "</a></b><br><br>";

        body += "<div style=color:#00008b;font-size:10.5pt;font-family:Calibri>Scenario: " + scenarioName + "</div><br>";
        body += "<div style=font-size:10.5pt;font-family:Calibri>Feature: " + linkToFeature + "</div><br>";

        body = _getEmailFooter(body);

        body += "</BODY>";

        email.setBody(body);
        email.setTo(project.getCollaborators());
        email.setCc(project.getEmailPo());

        LOG.info("getApprovedEmailTemplateFor Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;

    }

    public Email getFeedbackEmailTemplate(String comments)
    {

        Email email = new Email();

        email.setSubject("Feedback");

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "<div style=color:#1a894b,padding=10px 10px><b>Feedback:</b></div><br>";


        body += "<div style=color:#00008b;font-size:10.5pt;font-family:Calibri>" + comments + "</div><br>";

        body = _getEmailFooter(body);

        body += "</BODY>";

        email.setBody(body);
        email.setTo("kugajjar@paypal.com");

        LOG.info("getFeedbackEmailTemplate Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;

    }
}
