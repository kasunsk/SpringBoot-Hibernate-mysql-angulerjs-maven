package com.kasun.ailine.service.service.email;

import com.kasun.ailine.service.common.dto.EmailRequest;

/**
 * Created by kasun on 2/9/17.
 */
public interface EmailService {

    void sendEmail(EmailRequest emailRequest);
}
