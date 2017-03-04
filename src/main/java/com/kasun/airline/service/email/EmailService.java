package com.kasun.airline.service.email;

import com.kasun.airline.common.dto.EmailRequest;
import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.dto.email.EmailParam;

/**
 * Created by kasun on 2/9/17.
 */
public interface EmailService {

    void sendEmail(EmailRequest emailRequest);

    ServiceResponse<Boolean> sendEmail(ServiceRequest<EmailParam> emailParam);
}
