package com.cukesrepo.service.email;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class EmailServiceImpl implements EmailService
{

    private final EmailComponent _emailComponent;
//    private final String USERNAME = "cukes.repo@gmail.com";
//    private final String PASSWORD = "hackathon";

    private final String USERNAME = "kushang.ebay@gmail.com";
    private final String PASSWORD = "payal1218";

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    public EmailServiceImpl(EmailComponent emailComponent)
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


    private String _send(Email email) throws EmailException
    {

        LOG.info("Send email to '{}' with subject '{}'", email.getTo(), email.getSubject());

        try
        {

            Message message = new MimeMessage(_getSession());

            message.setFrom(new InternetAddress("do-not-reply@paypal.com"));
            message.setRecipients(Message.RecipientType.TO,
                                  InternetAddress.parse(email.getTo()));
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

    }

    private Session _getSession()
    {

        Properties props = new Properties();

        props.put("mail.debug", "true");
        props.put("mail.smtp.host", "smtp.paypal.com");

        return Session.getInstance(props,
                                   null);
    }

}
