package com.cukesrepo.service.email;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cukesrepo.component.EmailComponent;
import com.cukesrepo.domain.Email;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.EmailException;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CukeEmailServiceImpl implements CukeEmailService
{

    private final EmailComponent _emailComponent;

    private static final Logger LOG = LoggerFactory.getLogger(CukeEmailServiceImpl.class);

    @Autowired
    public CukeEmailServiceImpl(EmailComponent emailComponent)
    {

        Validate.notNull(emailComponent, "emailComponent cannot be null");

        _emailComponent = emailComponent;
    }

    @Override
    public String sendReviewRequest(Project project, Feature feature) throws EmailException
    {


        Email email = _emailComponent.getReviewEmailTemplateFor(project, feature);

        return _send(email);
    }


    @Override
    public String sendReviewComment(Project project, Feature feature, String scenarioName, String comment) throws EmailException
    {

        Email email = _emailComponent.getReviewCommentEmailTemplateFor(project, feature, scenarioName, comment);

        return _send(email);
    }

    @Override
    public String sendApproved(Project project, Feature feature, String scenarioName)
    {
        Email email = _emailComponent.getApprovedEmailTemplateFor(project, feature, scenarioName);

        return _send(email);

    }

    @Override
    public String sendFeedback(String comments, String feedbackType)
    {
        Email email = _emailComponent.getFeedbackEmailTemplate(comments, feedbackType);

        return _send(email);
    }

    @Override
    public void sendAddProjectRequest(Map<String, String[]> parameterMap)
    {
        Email email = _emailComponent.getAddProjectRequestTemplate(parameterMap);
        _send(email);
    }

    @Override
    public void sendProjectAddedEmail(Map<String, String[]> parameterMap)
    {
        Email email = _emailComponent.getProjectAddedTemplate(parameterMap);
        _send(email);
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

            if (StringUtils.isNotBlank(email.getCc()))
            {
                message.setRecipients(Message.RecipientType.CC,
                                      InternetAddress.parse(email.getCc()));
            }

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
        props.put("mail.smtp.host", "atom.paypalcorp.com");
        props.put("mail.transport.protocol", "smtp");

        return Session.getInstance(props,
                                   null);
    }

}
