package com.kasun.airline.service.email;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.common.service.RequestAssembler;
import com.kasun.airline.dto.email.EmailParam;
import com.kasun.airline.logic.email.EmailSendLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailSendLogic emailSendLogic;

    @Override
    public ServiceResponse<Boolean> sendEmail(ServiceRequest<EmailParam> emailParam) {
        return RequestAssembler.assemble(emailSendLogic, emailParam);
    }
}
