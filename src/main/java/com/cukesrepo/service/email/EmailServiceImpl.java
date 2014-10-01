package com.cukesrepo.service.email;

import com.cukesrepo.component.EmailComponent;
import com.cukesrepo.domain.Email;
import com.cukesrepo.domain.Feature;
import com.cukesrepo.domain.Project;
import com.cukesrepo.exceptions.EmailException;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


@org.springframework.stereotype.Service
public class EmailServiceImpl implements EmailService {

    private final EmailComponent _emailComponent;
    private final String USERNAME = "cukes.repo@gmail.com";
    private final String PASSWORD = "hackathon";

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    public EmailServiceImpl(EmailComponent emailComponent) {

        Validate.notNull(emailComponent, "emailComponent cannot be null");

        _emailComponent = emailComponent;
    }

    @Override
    public String sendReviewRequest(Project project, Feature feature) throws EmailException {

        Email email = _emailComponent.getReviewEmailTemplateFor(project, feature);

        return _send(email);
    }


    private String _send(Email email) throws EmailException {

        LOG.info("Send email to '{}' with subject '{}'", email.getTo(), email.getSubject());

        try {

            Message message = new MimeMessage(_getSession());

            message.setFrom(new InternetAddress("cuckes.repo@gmail.com", "kugajjar"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getTo()));
            message.setSubject(email.getSubject());
            message.setContent(email.getBody(), "text/html");

            Transport.send(message);

            LOG.info("Email sent successfully to '{}' with subject '{}'", email.getTo(), email.getSubject());

            return "Success";


        } catch (MessagingException e) {

            throw new EmailException("Email sent fail");

        } catch (UnsupportedEncodingException e) {

            throw new EmailException("Email sent fail");
        }
    }

    private Session _getSession() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
    }

}
