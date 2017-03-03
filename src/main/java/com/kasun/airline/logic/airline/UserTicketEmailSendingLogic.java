package com.kasun.airline.logic.airline;

import com.kasun.airline.common.dto.EmailRequest;
import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.execption.ServiceRuntimeException;
import com.kasun.airline.common.service.StatelessServiceLogic;
import com.kasun.airline.dao.airline.AirlineDao;
import com.kasun.airline.dao.email.EmailDao;
import com.kasun.airline.model.email.EmailModel;
import com.kasun.airline.model.user.User;
import com.kasun.airline.model.user.UserTicket;
import com.kasun.airline.service.email.EmailService;
import com.kasun.airline.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by kasun on 3/3/17.
 */
@Component
public class UserTicketEmailSendingLogic extends StatelessServiceLogic<Boolean, String> {

    private static final Logger logger = LoggerFactory.getLogger(UserTicketEmailSendingLogic.class);

    @Autowired
    private EmailDao emailHibernateDao;

    @Autowired
    private AirlineDao airlineHibernateDao;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private static String USER_TICKET_MAIL_SUBJECT = "Ticket Details";

    @Override
    public Boolean invoke(String userTicketId) {

        UserTicket userTicket = getUserTicket(userTicketId);
        String userId = userTicket.getUserId().toString();
        User user = getUser(userId);
        String emailBody = getContent(userTicket, user);
        String userEmail = user.getEmail();
        EmailModel emailModel = getEmailModel(emailBody, userEmail);
        EmailRequest emailRequest = getEmailRequest(emailBody, userEmail);

        try {
            emailService.sendEmail(emailRequest);
            emailModel.setStatus(EmailModel.EmailStatus.SENT);
        } catch (ServiceRuntimeException ex) {
            logger.error("Email Sending Failed", ex);
            emailModel.setStatus(EmailModel.EmailStatus.FAILED);
        }
        emailHibernateDao.saveEmailData(emailModel);
        return Boolean.TRUE;
    }

    private User getUser(String userId) {
        return userService.loadUserById(new ServiceRequest<>(userId)).getPayload();
    }

    private UserTicket getUserTicket(String userTicketId) {
        return airlineHibernateDao.loadUserTicketById(Long.parseLong(userTicketId));
    }

    private EmailRequest getEmailRequest(String emailBody, String userEmail) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setBody(emailBody);
        emailRequest.setToEmail(userEmail);
        emailRequest.setSubject(USER_TICKET_MAIL_SUBJECT);
        return emailRequest;
    }

    private EmailModel getEmailModel(String emailBody, String userEmail) {
        EmailModel emailModel = new EmailModel();
        emailModel.setReceiverMailAddress(userEmail);
        emailModel.setSubject(USER_TICKET_MAIL_SUBJECT);
        emailModel.setStatus(EmailModel.EmailStatus.SENDING);
        emailModel.setContent(emailBody);
        return emailModel;
    }

    private String getContent(UserTicket userTicket, User user) {

        StringBuilder mailBodyBuilder = new StringBuilder();
        mailBodyBuilder.append("Name : ").append(user.getName());
        mailBodyBuilder.append("User Ticket Id : ").append(userTicket.getId());
        mailBodyBuilder.append("Origin : ").append(userTicket.getOrigin());
        mailBodyBuilder.append("Destination : ").append(userTicket.getDestination());
        mailBodyBuilder.append("Price ").append(userTicket.getPrice()).append(" ").append(userTicket.getCurrency());
        mailBodyBuilder.append("Number of Tickets ").append(userTicket.getTicketsAmount());
        return mailBodyBuilder.toString();
    }
}
