package com.kasun.airline.service.email;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;
import com.kasun.airline.dto.email.EmailParam;

public interface EmailService {

    ServiceResponse<Boolean> sendEmail(ServiceRequest<EmailParam> emailParam);
}
