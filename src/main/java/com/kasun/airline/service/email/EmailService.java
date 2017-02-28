package com.kasun.airline.service.email;

import com.kasun.airline.common.dto.EmailRequest;

/**
 * Created by kasun on 2/9/17.
 */
public interface EmailService {

    void sendEmail(EmailRequest emailRequest);
}
