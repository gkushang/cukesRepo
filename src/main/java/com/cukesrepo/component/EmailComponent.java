package com.cukesrepo.component;


import java.util.Map;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.cukesrepo.domain.Email;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.EmailException;
import com.cukesrepo.utils.Utils;
import org.apache.commons.lang.Validate;
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

    public Email getFeedbackEmailTemplate(String comments, String feedbackType)
    {

        Email email = new Email();

        email.setSubject("Feedback");

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "<div style=color:#1a894b,padding=10px 10px><b>" + feedbackType + ":</b></div><br>";


        body += "<div style=color:#00008b;font-size:10.5pt;font-family:Calibri>" + comments + "</div><br>";

        body = _getEmailFooter(body);

        body += "</BODY>";

        email.setBody(body);
        email.setTo("kugajjar@paypal.com");

        LOG.info("getFeedbackEmailTemplate Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;

    }

    public Email getAddProjectRequestTemplate(Map<String, String[]> parameterMap)
    {

        LOG.info("Add project Request '{}'", parameterMap);

        String projectName = parameterMap.get("projectname")[0];
        String repositoryPath = parameterMap.get("repositorypath")[0];
        String poEmail = parameterMap.get("emailofpo")[0];
        String collaborators = parameterMap.get("collaborators")[0];

        Validate.notEmpty(projectName, "Enter Project Name");
        Validate.notEmpty(repositoryPath, "Enter Github SSH Clone URL");
        Validate.notEmpty(poEmail, "Enter PO email address");
        Validate.notEmpty(collaborators, "Enter collaborators email address");

        _validateEmail(poEmail);

        LOG.info("send request to add project '{}'", projectName);

        Email email = new Email();

        email.setSubject("Add Project");

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";


        body += "<div style=color:#00008b; padding=10px 10px>" + "We would like to thank you for adding your project to Cukes Repo. Your request " +
                "is under progress. You will be notified by email when your project is added." + "</div><br><br>";


        body += "<div style=color:#00008b;font-size:10.5pt;font-family:Calibri><b>---</b></div>";
        body += "<div style=color:#00008b;font-size:10.5pt;font-family:Calibri>" + projectName + "</div>";

        body += "<div style=color:#00008b;font-size:10.5pt;font-family:Calibri>" + repositoryPath + "</div>";

        body += "<div style=color:#00008b;font-size:10.5pt;font-family:Calibri>" + collaborators + "</div>";

        body += "<div style=color:#00008b;font-size:10.5pt;font-family:Calibri>" + poEmail + "</div><br>";

        body = _getEmailFooter(body);

        body += "</BODY>";

        email.setBody(body);
        email.setTo("kugajjar@paypal.com");

        LOG.info("getFeedbackEmailTemplate Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;


    }

    private void _validateEmail(String email)
    {
        try
        {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        }
        catch (AddressException e)
        {
            throw new EmailException(e.getMessage(), e);
        }
    }

    public Email getProjectAddedTemplate(Map<String, String[]> parameterMap)
    {
        LOG.info("Add project Request '{}'", parameterMap);

        String projectName = parameterMap.get("projectname")[0];
        String repositoryPath = parameterMap.get("repositorypath")[0];
        String poEmail = parameterMap.get("emailofpo")[0];
        String collaborators = parameterMap.get("collaborators")[0];

        Validate.notEmpty(projectName, "Enter Project Name");
        Validate.notEmpty(repositoryPath, "Enter Github SSH Clone URL");
        Validate.notEmpty(poEmail, "Enter PO email address");
        Validate.notEmpty(collaborators, "Enter collaborators email address");

        _validateEmail(poEmail);

        LOG.info("send request to add project '{}'", projectName);

        Email email = new Email();

        email.setSubject(projectName + " is added to cukesRepo");

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "<div style=color:#00008b; padding=9px 10px><a href=\"http://go/cukes\">" + projectName + "</a> is added to cukesRepo. You can visit <a href=\"http://cukes-3793.lvs01.dev.ebayc3.com/" + projectName + "/\" style=font-size:10pt>go/cukes</a> to view your project's features and scenarios." + "</div><br>";

        body += "<div style=color:#00008b; font-size:10.5pt;font-family:Calibri>We would like to thank you for using cukesRepo! Please let us know if you face any issues by visiting <a href=\"http://cukes-3793.lvs01.dev.ebayc3.com/feedback/\">feedback</a> or send an email to kugajjar@paypal.com.</div><br>";

        body = _getEmailFooter(body);

        body += "</BODY>";

        email.setBody(body);
        email.setTo("kugajjar@paypal.com");

        LOG.info("getFeedbackEmailTemplate Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;
    }
}
