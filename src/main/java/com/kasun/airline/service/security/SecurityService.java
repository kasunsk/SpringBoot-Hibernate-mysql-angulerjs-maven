package com.kasun.airline.service.security;

import com.kasun.airline.common.dto.ServiceRequest;
import com.kasun.airline.common.dto.ServiceResponse;

/**
 * Created by kasun on 3/5/17.
 */
public interface SecurityService {

    ServiceResponse<String> encrypt(ServiceRequest<String> word);
}
