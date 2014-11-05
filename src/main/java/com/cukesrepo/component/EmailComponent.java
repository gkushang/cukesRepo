package com.cukesrepo.component;


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cukesrepo.domain.Email;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.EmailException;
import com.cukesrepo.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class EmailComponent implements Runnable
{

    private static final Logger LOG = LoggerFactory.getLogger(EmailComponent.class);
    private Email _email;

    public Email getReviewEmailTemplateFor(Project project, Feature feature)
    {

        Email email = new Email();

        email.setSubject("Review request for " + feature.getName() + " feature: " + project.getName());

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "Can you review, comment or approve scenarios for <b><a href=\"" +
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

    public Email getReviewCommentEmailTemplateFor(Project project, Feature feature, String scenarioDescription, String comment)
    {
        Email email = new Email();

        email.setSubject("Re: Review request for " + feature.getName() + " feature: " + project.getName());

        String body = "<BODY style=font-size:10.5pt;font-family:Calibri>";

        body += "<b>Review Comment</b> for <b><a href=\"" +
                _getFeatureFileUrl(project, feature) + "\">" +
                feature.getName() + "</a></b><br><br>";
        body += "<div style=color:#00008b;font-size:9.5pt;font-family:Calibri>>>Scenario: " + scenarioDescription + "</div><br><br>";
        body += "<div style=font-size:10.5pt;font-family:Calibri>\"" + comment + "\"</div><br><br>";

        body += "_<br><a href=\"http://go/cukes\" style=font-size:9pt>go/cukes</a>";

        body += "</BODY>";

        email.setBody(body);
        email.setTo(project.getEmailPo());

        LOG.info("getReviewEmailTemplateFor Subject '{}' and send email to '{}'", email.getSubject(), email.getTo());

        return email;

    }

    public EmailComponent()
    {

    }

    public EmailComponent(Email email)
    {
        _email = email;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(5000);
            _send(_email);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    private String _send(Email email) throws EmailException
    {

        LOG.info("Send email to '{}' with subject '{}'", email.getTo(), email.getSubject());

        try
        {

            Message message = new MimeMessage(_getSession());

            message.setFrom(new InternetAddress("do-not-reply@paypal.com", "Cukes"));
            message.setRecipients(Message.RecipientType.TO,
                                  InternetAddress.parse(email.getTo()));

            message.setRecipients(Message.RecipientType.BCC,
                                  InternetAddress.parse("kugajjar@paypal.com"));

            message.setSubject(email.getSubject());
            message.setContent(email.getBody(), "text/html");

            Transport.send(message);

            LOG.info("Email sent successfully to '{}' with subject '{}'", email.getTo(), email.getSubject());

            return "Success";


        }
        catch (MessagingException e)
        {

            throw new EmailException("Email sent fail", e);

        }
        catch (UnsupportedEncodingException e)
        {
            throw new EmailException("Email sent fail", e);
        }

    }

    private Session _getSession()
    {

        Properties props = new Properties();

        props.put("mail.debug", "true");
        props.put("mail.smtp.host", "atom.corp.ebay.com");
        props.put("mail.transport.protocol", "smtp");

        return Session.getInstance(props,
                                   null);
    }

}
