package com.kasun.ailine.service.service.email;

import com.kasun.ailine.service.common.dto.EmailRequest;
import com.kasun.ailine.service.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by kasun on 2/9/17.
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment environment;

    @Override
    public void sendEmail(EmailRequest emailRequest) {

        Properties props = System.getProperties();
        String smtpHostServer = environment.getProperty("mail.smtp.host");
        props.put("mail.smtp.host", smtpHostServer);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("kasunsk@gmail.com","sooo3yaek");
                    }
                });

     //   EmailUtil.sendEmail(session, emailRequest.getToEmail(), emailRequest.getSubject(), emailRequest.getBody());

        EmailUtil.send(session);
    }
}
