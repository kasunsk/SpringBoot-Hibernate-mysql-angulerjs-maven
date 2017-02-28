package com.crossover.techtrial.java.se.service.email;

import com.crossover.techtrial.java.se.common.dto.EmailRequest;

/**
 * Created by kasun on 2/9/17.
 */
public interface EmailService {

    void sendEmail(EmailRequest emailRequest);
}
